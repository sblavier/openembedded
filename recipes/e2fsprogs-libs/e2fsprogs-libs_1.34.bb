require e2fsprogs-libs.inc
PR = "r1"
SRC_URI += "file://ldflags.patch;patch=1"

do_compile_prepend () {
	find ./ -print|xargs chmod u=rwX
	( cd util; ${BUILD_CC} subst.c -o subst )
}
