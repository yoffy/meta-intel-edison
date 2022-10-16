# x32-minimal

## What is x32 ABI

x32 ABI is better performance than i386 ABI and smaller memory footprint than x86_64 ABI.

See: https://en.wikipedia.org/wiki/X32_ABI

## Prepare tools

In Ubuntu 20.04:

```
$ sudo apt install diffstat unzip texinfo gcc-multilib build-essential chrpath socat cpio python python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping libsdl1.2-dev xterm btrfs-progs p7zip-full dfu-util
```

## Build and flash minimal image

```
$ mkdir edison
$ cd edison
$ git clone https://github.com/yoffy/meta-intel-edison.git
$ ln -s meta-intel-edison/utils/Makefile.mk Makefile
$ make setup
$ make image-minimal
$ make postbuild-minimal
$ sudo make flash
```

## Expand root filesystem

```
$ resize2fs /dev/mmcblk0p8
```

## Setup WiFi

Create WiFi settings that ssid and password:
```
$ mkdir /etc/wpa_supplicant
$ vi /etc/wpa_supplicant/wpa_supplicant-wlan0.conf
ctrl_interface=/var/run/wpa_supplicant
update_config=1

network={
        ssid="YOUR-SSID"
        psk="YOUR-PASSWORD"
}
```

Launch wpa_supplicant automatically:
```
$ systemctl enable wpa_supplicant@wlan0
$ systemctl start wpa_supplicant@wlan0
```

Enable DHCP:
```
$ vi /etc/systemd/network/wlan0.network
[Match]
Name=wlan0

[Network]
DHCP=yes

$ systemctl restart systemd-networkd
$ ip a
3: wlan0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast qlen 1000
...
```

## Enable Bluetooth

```
$ rfkill unblock bluetooth
$ hciconfig hci0 up
$ bluetoothctl
Agent registered
[CHG] Controller XX:XX:XX:XX:XX:XX Pairable: yes
[bluetooth]# power on
Changing power on succeeded
[bluetooth]# exit
```
