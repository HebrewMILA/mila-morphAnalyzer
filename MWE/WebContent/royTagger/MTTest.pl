#!/usr/bin/perl -w

#MTtest

#test phase for morphtagger

use strict;

#my $mtdir = "/home/gennadil/HMMTagger/royTagger";
$_ = $0;
m/^(.*)\/.*$/;
my $mtdir = $1;

######################### parse command line #####################
if ($#ARGV < 0 || $ARGV[0] eq "-help") {
  print STDERR <<"EOF";

MorphTagger Version 1.0. Copyright 2005, Roy Bar-Haim

Usage: MTTest.pl [options] analysesfile [lmfile lexprobfile] [outputfile]

analysesfile    input sentences to disambiguate.

outputfile      output file for the tagging result. If ommited the output will
                be written to workdir/tagging-analysesfile

lmfile          language model file. Generated by MTlearn. If not specified,
				workdir/corpus.lm is used.
				
lexprobfile     lexical probabilities file. Generated by MTLearn. If not specified,
				workdir/corpus.lex.prob is used.

OPTIONS:
-dir workdirname
                work directory name. The default value is .MorphTaggerWorkDir.
                If using -gold,	the same learning directory should be used!

-remove-corpus-analyses
                When this option is specified, MorphTagger wont add the analyses
                found in the corpus to the analyses file. By default the analyses
                are added as this improves results usually.

-nbest number   print nbest tagged sequences and their probabilities
-svm svmoutput  tagged file generated using POSrun (SVM) over the analyses file, 
                in MorphTagger output format.
-eval goldtagging
                perform evaluation using MorphEvalExtended.pl. goldtagging includes
                tagging of the analyses in roy learning format.
-gold goldsegmentation tagsfile
                goldsegmentation:
                A file containing correct segmentations of test words. The
                file format is in the same format as the corpus file in the
                documentation, except that the POS tags are ommited. Example:
                w1 m1 m2 ... mn.
                tagsfile:
                A file containing possible tags that will be given to the morphemes
                of a word that has no correct segmentation in the analysis file.
-no_func        ignore NO_FUNC from evaluation               
-help           print this message

EOF
  exit;
}


############ setting the defaults
my $analysesfile = "";
my $outfile = "";
my $logfile = "log.txt";
my $lmfile = "";
my $lexprobfile = "";
my $workdir = ".MorphTaggerWorkDir";

my $addcorpusanalyses=0;
my $remove_corpus_analyses = 0;

#$cleanworkdir = 0;

my $ignorenofunc = "";
my $removetmp = 0;

#------------------
# REUT 24-10-2005
#------------------
# added nbest argument 
# to command line args
my $nbest = 1;
my $printprob = 0;
#-----------------


my $analysesname;
my $svmoutput = "";
my ($goldtaggingmt, $goldtagging) = ("","");

my $goldsegmentation = "";
my $tagsfile = "";
my $gold_run = 0;
############## parsing parameters
my $ok =0;
my $command = join(" ", @ARGV);

while (@ARGV) {
  my $arg = shift @ARGV;

  ### -workdir <dirname> (dedault:.MorphTagger)
  if ($arg eq "-dir") {
    die "missing argument for -dir\n" unless (@ARGV);
    $workdir = shift @ARGV;
  }
  
  ###-no_func
  elsif ($arg eq "-no_func") {
	$ignorenofunc = $arg;
  }
  
  elsif ($arg eq "-rmtmp") {
	$removetmp = 1;
  }


  ###-svm svmoutput
  elsif ($arg eq "-svm") {
	die "missing argument for -svm\n" unless (@ARGV);
    $svmoutput = shift @ARGV;
	die "$svmoutput was not found\n" unless -e $svmoutput;
  }
  
  ###-eval goldtagging
  elsif ($arg eq "-eval") {
    die "missing argument for -eval\n" unless (@ARGV);
    $goldtagging = shift @ARGV;
	die "$goldtagging was not found\n" unless -e $goldtagging;
  }
  
  ### -remove-corpus-analyses
  elsif ($arg eq "-remove-corpus-analyses") {
    $remove_corpus_analyses = 1;
  }

	#------------------
	# REUT 24-10-2005
	#------------------
	# added nbest argument to command line
   elsif ($arg eq "-nbest") {
   die "missing argument for -nbest\n" unless (@ARGV);
   my $newnbest = shift @ARGV;
   if ($newnbest >0 && $newnbest <100) {
        $nbest = $newnbest;
		$printprob = 1;
		#print "printprob $printprob\n";
    }
    else {
      die "Invalid value for -nbest\n";
    }  
  }
#  elsif($arg eq "-clean-workdir") { 
#    $cleanworkdir = 1;   
#  }

  ### -gold <goldsegmentation>
  elsif ($arg eq "-gold") {
    die "missing argument for -gold\n" unless (@ARGV>=2);
    $goldsegmentation = shift @ARGV;
	die "$goldsegmentation was not found\n" unless -e $goldsegmentation;
	$tagsfile = shift @ARGV;
	die "$tagsfile was not found\n" unless -e $tagsfile;
	$gold_run = 1;
  }
  
  ### not an option - we got to the required parameters
  else {
    die "Unknown option '$arg'; type $0 -help for information\n"
      if ($arg =~ /^-/);
    $analysesfile=$arg;
    die "$analysesfile was not found\n" unless -e $analysesfile;
	
	$analysesname = $analysesfile;
	if ($analysesfile =~ /\/([^\/]+)$/){
		$analysesname = $1;
	}
    if (scalar(@ARGV)==3) {
      ($lmfile,$lexprobfile,$outfile)=@ARGV;
      #die "$corpusfile was not found\n" unless -e $corpusfile;
      $ok=1;
    }
	elsif (scalar(@ARGV)==2) {
      ($lmfile,$lexprobfile)=@ARGV;
	  $outfile = "$workdir/tagging-$analysesname";
      $ok=1;
    }
	elsif (scalar(@ARGV)==1) {
      ($outfile)=@ARGV;      
      $ok=1;
    }
	elsif (scalar(@ARGV)==0) {
      $outfile = "$workdir/tagging-$analysesname";
      $ok=1;
    }
    last;
  } 
}

unless ($ok) {
  #die "Too many arguments ; type $0 -help for information\n" if (scalar(@ARGV)>3);
  #die "Too few arguments ;  type $0 -help for information\n" if (scalar(@ARGV)<3); 
  die "wrong arguments ;  type $0 -help for information\n"; 
}
  
`mkdir $workdir` unless -e $workdir;

if (!$lmfile){
	$lmfile = "$workdir/corpus.lm";
	$lexprobfile = "$workdir/corpus.lex.prob";
}




$logfile = "$workdir/$logfile";
open(LOG, ">$logfile") or die "can't open $logfile: $!";
my $logstr = "";
my $retstderr = "2>&1";

my $order = &GetOrder($lmfile);

#generate joined file
my $allanalyses = $analysesfile;
my $svm = 0;
if ($svmoutput){
	$allanalyses = "$workdir/joined-file";
	$logstr .= `join-buck-archunk.pl -svm $analysesfile $svmoutput $allanalyses $retstderr`;
	$svm = 1;
}

######################### Tagging #####################

my $revmapfile = "$workdir/$analysesname.analyses.morph.revmap";

$logstr=$logstr . "command: $command\n" . "analyses file: $allanalyses\n" .	
				"language model file: $lmfile\n" . "Order: $order\n" . 
				"NBest: $nbest\n" . 
				"Output probability: " . (($printprob)?"yes":"no") . "\n" .
				"lexical probabilities: $lexprobfile" . "\n" .
				"working directory: $workdir" . "\n" .
				(($gold_run)?"gold run gold segmenatation: $goldsegmentation\t,\tpossible tags $tagsfile":"normal run") . "\n" . 
				(($goldtagging)?"evaluate, gold file: $goldtagging":"no evaluation") . "\n" .
				(($remove_corpus_analyses)?"corpus analyses not used":"corpus analyses used") . "\n" . 
				(($svm)?"svm file: $svmoutput\n":"");
#print $logstr;

#normal run
if (! $gold_run){
print "perl -I$mtdir $mtdir/CreateMorphModel.pl $workdir/$analysesname.corpus.morph.lm $workdir/$analysesname.analyses.morph $allanalyses $lmfile $lexprobfile $remove_corpus_analyses $svm $retstderr\n";
	$logstr .= `perl -I$mtdir $mtdir/CreateMorphModel.pl $workdir/$analysesname.corpus.morph.lm $workdir/$analysesname.analyses.morph $allanalyses $lmfile $lexprobfile $remove_corpus_analyses $svm $retstderr`;
	##### run tagger
	`rm -f $outfile`;
	#------------------
	# REUT 24-10-2005
	#------------------
	# added nbest argument to disambig
print "perl -I$mtdir $mtdir/RunTagger.pl $workdir/$analysesname.analyses.morph.map $workdir/$analysesname.analyses.morph.revmap $workdir/$analysesname.corpus.morph.lm $order $outfile $workdir normal $nbest $printprob $svm $retstderr\n"; 
	$logstr .= `perl -I$mtdir $mtdir/RunTagger.pl $workdir/$analysesname.analyses.morph.map $workdir/$analysesname.analyses.morph.revmap $workdir/$analysesname.corpus.morph.lm $order $outfile $workdir "normal" $nbest $printprob $svm $retstderr`;
	#------------------
	
	if ($removetmp){
		`rm -f $workdir/$analysesname.analyses.morph.map* $workdir/$analysesname.analyses.morph.revmap $workdir/$analysesname.corpus.morph.lm`;
	}
}
else{
	#gold run
	print STDERR "creating morph map file...\n";
	$logstr .= "creating morph map file...\n";
	$logstr .= `perl -I$mtdir $mtdir/CreateMorphMapFile.pl $workdir/$analysesname.analyses.morph.map $tagsfile $lexprobfile $goldsegmentation $allanalyses $svm $retstderr` if (! -e "$analysesname.analyses.morph.map");
	
	#change goldsegmentation to (not exactly) rev map file format <s> <s> [..] [..] ...
	`perl -I$mtdir $mtdir/CorpusNoTagsToREV.pl < $goldsegmentation > $workdir/goldrevf`;
	`cp $workdir/goldrevf $workdir/goldrevf.svm`;
	#`rm -fr $workdir/Run`;
	#mkdir "$workdir/Run";
	#`SplitTextMapFile.pl $workdir/Run/ < $workdir/smalltest.map`;
	#`SplitFile.pl $workdir/Run/ < $workdir/mtgold`;

	print STDERR "running gold tagger...\n";
	$logstr .= `perl -I$mtdir $mtdir/RunTagger.pl $workdir/$analysesname.analyses.morph.map $workdir/goldrevf $lmfile $order $outfile $workdir "gold" $nbest $printprob $svm $retstderr`;

	#`RunTagger.pl $workdir/Run $lmfile $order $nbest`;
	#`GetGoodResultsHMM.sc $workdir/Run`;

	my $suffix = $analysesfile;
	if ($suffix =~ /\/([^\/]+)$/){
		$suffix = $1;
	}

	#`mv goodIDs $workdir/goodIDs`;
	`mv $outfile $workdir/gold-tagging-$suffix`;
	print STDERR "Output written to $workdir/gold-tagging-$suffix\n";
	$logstr .= "Output written to $workdir/gold-tagging-$suffix\n";

	$outfile = "$workdir/gold-tagging-$suffix";
	$revmapfile = "";
}

CheckStat("Disambiguation");

#evaluate
if ($goldtagging){
	#create MT format from $goldtagging
	my $goldtaggingname = $goldtagging;
	if ($goldtaggingname =~ /\/([^\/]+)$/){
		$goldtaggingname = $1;
	}
	$goldtaggingmt = $goldtaggingname.".mt";
	`roycorpus-to-MTout.pl < $goldtagging > $workdir/$goldtaggingmt`;
	
	print STDERR "Evaluating...\n";
	$logstr .= "Evaluating...\n";
	$logstr .= `$mtdir/MorphEvalExtended.pl $ignorenofunc $workdir/$goldtaggingmt $outfile empty $goldtagging $revmapfile $retstderr`;
}

####################### Adjust the output ########################

print STDERR "\nDONE!\n";

print LOG $logstr;
close(LOG);

sub CheckStat {
  my $status = $?;
  if ($status) {
     printf STDERR "%s failed (exit status %d)\nMorphTagger terminated\n",$_[0],$status; 
     exit $status; 
  }
}




sub GetOrder(){
	my $lmfile = shift @_;
	my $order = -1;
	
	open(LM,"<$lmfile") or die "GetOrder- couldn't open $lmfile: $!\n";
	while((my $line = <LM>) !~ /^\\1-grams:\s*$/){
		if ($line=~/^ngram (\d+)=\d+\s*$/){
			$order = $1;
		}
	}
	
	close(LM);
	
	if ($order<1) {die "couldn't retrieve order from $lmfile\n";}
	return $order;
	
}

