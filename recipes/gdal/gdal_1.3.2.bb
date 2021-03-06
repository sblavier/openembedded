DESCRIPTION = "GDAL is a translator library for raster geospatial data formats"
HOMEPAGE = "http://www.gdal.org/"
LICENSE = "MIT"
DEPENDS = "zlib"

SRC_URI = "http://www.gdal.org/dl/${P}.tar.gz"

inherit autotools pkgconfig binconfig

PARALLEL_MAKE = ""

EXTRA_OECONF = "--without-perl \
                --without-python \
                --without-php \
                --without-ruby \
                --with-libz=internal \
                --with-png=internal \
                --with-jpeg=internal \
                --with-libtiff=internal \
                "

do_configure_append() {
	        sed -i s:/usr/lib:${D}${libdir}: GDALmake.opt
	        sed -i s:/usr/bin:${D}${bindir}: GDALmake.opt
	        sed -i s:/usr/share:${D}${datadir}: GDALmake.opt
	        sed -i s:/usr/include:${D}${includedir}: GDALmake.opt
}

do_compile() {
		oe_runmake default
}


do_package_prepend() {
	# the brokenness....
	os.system('cp -pPR ${D}${D}* ${D}../')
}

do_stage() {
        autotools_stage_all
}	

