DESCRIPTION = "An e17 module and a python app for the SHR first run wizard"
HOMEPAGE = "http://shr-project.org"
LICENSE = "GPL"
RDEPENDS = "python-elementary shr-settings python-phoneutils e-wm python-dbus python-edbus"
SECTION = "x11/application"
PV = "0.0.0+gitr${SRCREV}"
PR = "r1"

inherit autotools

SRC_URI = "git://git.shr-project.org/repo/shr-wizard.git;protocol=http;branch=master"
S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/enlightenment/modules/wizard/*/page_900.so"


