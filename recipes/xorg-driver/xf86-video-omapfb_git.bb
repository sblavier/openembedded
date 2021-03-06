require xorg-driver-video.inc

DESCRIPTION = "X.Org X server -- OMAP display driver"

PR_append = "d"

SRCREV = "2ae9a57ebc3236cd0aec6534067b91a5c6916a3a"
PV = "0.1.1+${PR}+gitr${SRCREV}"
PE = "1"

SRC_URI = "git://git.pingu.fi/xf86-video-omapfb.git;protocol=http \
          "

S = "${WORKDIR}/git"

EXTRA_OECONF_armv7a = " --enable-neon "
CFLAGS += " -I${STAGING_INCDIR}/xorg "

# Use overlay 2 on omap3 to enable other apps to use overlay 1 (e.g. dmai or omapfbplay)
do_compile_prepend_armv7a () {
	sed -i -e s:fb1:fb2:g ${S}/src/omapfb-xv.c
}

