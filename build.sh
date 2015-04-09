


function build_project {
    local current=$(pwd)
	cd $1
	boot package build-jar install
	cd $current
}


build_project "react-bootstrap"
build_project "react-bootstrap-datetimepicker"

