DESCRIPTION = "sketch check"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/:"

inherit systemd

SYSTEMD_SERVICE:${PN} = "sketch-check.service"
SYSTEMD_AUTO_ENABLE = "disable"

SRC_URI = "file://sketch-check.sh"
SRC_URI += "file://sketch-check.service"

S = "${WORKDIR}"

do_install() {
        install -d ${D}${bindir}
        install -m 0755 sketch-check.sh ${D}${bindir}

        # Copy service file
        install -d ${D}/${systemd_unitdir}/system
        install -c -m 644 ${WORKDIR}/sketch-check.service ${D}/${systemd_unitdir}/system
}

FILES:${PN} = "${base_libdir}/systemd/system/sketch-check.service"
FILES:${PN} += "${bindir}/sketch-check.sh"
