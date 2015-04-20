PORT=${1:-8088} 
HOST=localhost:$PORT/MWE/analysis
function prep() {
	curl -L --data "input_text=$(cat $1)" $HOST > $2
}
cd generated
prep templates/INDEX index.xml
prep templates/ERROR error.xml
