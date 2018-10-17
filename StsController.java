[K[root@iZ2zebby2spwhc553j2avtZ vsftpd]# 
[root@iZ2zebby2spwhc553j2avtZ vsftpd]# 
[root@iZ2zebby2spwhc553j2avtZ vsftpd]# ls
[0m[01;34mftplogin[0m  ftpusers  login.db  user_list  vsftpd.conf  [01;32mvsftpd_conf_migrate.sh[0m  vuser
[root@iZ2zebby2spwhc553j2avtZ vsftpd]# ^C
[root@iZ2zebby2spwhc553j2avtZ vsftpd]# ls
[0m[01;34mftplogin[0m  ftpusers  login.db  user_list  vsftpd.conf  [01;32mvsftpd_conf_migrate.sh[0m  vuser
[root@iZ2zebby2spwhc553j2avtZ vsftpd]# service iptables stop
Redirecting to /bin/systemctl stop iptables.service
Failed to stop iptables.service: Unit iptables.service not loaded.
[root@iZ2zebby2spwhc553j2avtZ vsftpd]# vi vsftpd.conf 
[?1h=[1;56r[m[m[0m[H[J[56;1H"vsftpd.conf" 127L, 5030C[1;1H# Example config file /etc/vsftpd/vsftpd.conf
#
# The default compiled in settings are fairly paranoid. This sample file
# loosens things up a bit, to make the ftp daemon more usable.
# Please see vsftpd.conf.5 for all compiled in defaults.
#
# READ THIS: This example file is NOT an exhaustive list of vsftpd options.
# Please read the vsftpd.conf.5 manual page to get a full idea of vsftpd's
# capabilities.
#
# Allow anonymous FTP? (Beware - allowed by default if you comment this out).
anonymous_enable=YES
#
# Uncomment this to allow local users to log in.
# When SELinux is enforcing check for SE bool ftp_home_dir
local_enable=YES
#
# Uncomment this to enable any form of FTP write command.
write_enable=YES
#
# Default umask for local users is 077. You may wish to change this to 022,
# if your users expect that (022 is used by most other ftpd's)
local_umask=022
#
# Uncomment this to allow the anonymous FTP user to upload files. This only
# has an effect if the above global write enable is activated. Also, you will
# obviously need to create a directory writable by the FTP user.
# When SELinux is enforcing check for SE bool allow_ftpd_anon_write, allow_ftpd_full_access
#anon_upload_enable=YES
#
# Uncomment this if you want the anonymous FTP user to be able to create
# new directories.
#anon_mkdir_write_enable=YES
#
# Activate directory messages - messages given to remote users when they
# go into a certain directory.
dirmessage_enable=YES
#
# Activate logging of uploads/downloads.
xferlog_enable=YES
#
# Make sure PORT transfer connections originate from port 20 (ftp-data).
connect_from_port_20=YES
#
# If you want, you can arrange for uploaded anonymous files to be owned by
# a different user. Note! Using "root" for uploaded files is not
# recommended!
#chown_uploads=YES
#chown_username=whoever
#
# You may override where the log file goes if you like. The default is shown
# below.
#xferlog_file=/var/log/xferlog
#
# If you want, you can have your log file in standard ftpd xferlog format.[1;1H





















































[1;55r[55;1H
[1;56r[55;1H# Note that the default log file location is /var/log/xferlog in this case.[56;1H[K[55;1H[1;55r[55;1H
[1;56r[55;1Hxferlog_std_format=YES[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# You may change the default value for timing out an idle session.[1;55r[55;1H
[1;56r[55;1H#idle_session_timeout=600[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# You may change the default value for timing out a data connection.[1;55r[55;1H
[1;56r[55;1H#data_connection_timeout=120[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# It is recommended that you define on your system a unique user which the[1;55r[55;1H
[1;56r[55;1H# ftp server can use as a totally isolated and unprivileged user.[1;55r[55;1H
[1;56r[55;1H#nopriv_user=ftpsecure[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# Enable this and the server will recognise asynchronous ABOR requests. Not[1;55r[55;1H
[1;56r[55;1H# recommended for security (the code is non-trivial). Not enabling it,[1;55r[55;1H
[1;56r[55;1H# however, may confuse older FTP clients.[1;55r[55;1H
[1;56r[55;1H#async_abor_enable=YES[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# By default the server will pretend to allow ASCII mode but in fact ignore[1;55r[55;1H
[1;56r[55;1H# the request. Turn on the below options to have the server actually do ASCII[1;55r[55;1H
[1;56r[55;1H# mangling on files when in ASCII mode.[1;55r[55;1H
[1;56r[55;1H# Beware that on some FTP servers, ASCII support allows a denial of service[1;55r[55;1H
[1;56r[55;1H# attack (DoS) via the command "SIZE /big/file" in ASCII mode. vsftpd[1;55r[55;1H
[1;56r[55;1H# predicted this attack and has always been safe, reporting the size of the[1;55r[55;1H
[1;56r[55;1H# raw file.[1;55r[55;1H
[1;56r[55;1H# ASCII mangling is a horrible feature of the protocol.[1;55r[55;1H
[1;56r[55;1H#ascii_upload_enable=YES[1;55r[55;1H
[1;56r[55;1H#ascii_download_enable=YES[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# You may fully customise the login banner string:[1;55r[55;1H
[1;56r[55;1H#ftpd_banner=Welcome to blah FTP service.[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# You may specify a file of disallowed anonymous e-mail addresses. Apparently[1;55r[55;1H
[1;56r[55;1H# useful for combatting certain DoS attacks.[1;55r[55;1H
[1;56r[55;1H#deny_email_enable=YES[1;55r[55;1H
[1;56r[55;1H# (default follows)[1;55r[55;1H
[1;56r[55;1H#banned_email_file=/etc/vsftpd/banned_emails[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# You may specify an explicit list of local users to chroot() to their home[1;55r[55;1H
[1;56r[55;1H# directory. If chroot_local_user is YES, then this list becomes a list of[1;55r[55;1H
[1;56r[55;1H# users to NOT chroot().[1;55r[55;1H
[1;56r[55;1H# (Warning! chroot'ing can be very dangerous. If using chroot, make sure that[1;55r[55;1H
[1;56r[55;1H# the user does not have write access to the top level directory within the[1;55r[55;1H
[1;56r[55;1H# chroot)[1;55r[55;1H
[1;56r[55;1H#chroot_local_user=YES[1;55r[55;1H
[1;56r[55;1H#chroot_list_enable=YES[1;55r[55;1H
[1;56r[55;1H# (default follows)[1;55r[55;1H
[1;56r[55;1H#chroot_list_file=/etc/vsftpd/chroot_list[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# You may activate the "-R" option to the builtin ls. This is disabled by[1;55r[55;1H
[1;56r[55;1H# default to avoid remote users being able to cause excessive I/O on large[1;55r[55;1H
[1;56r[55;1H# sites. However, some broken FTP clients such as "ncftp" and "mirror" assume[1;55r[55;1H
[1;56r[55;1H# the presence of the "-R" option, so there is a strong case for enabling it.[1;55r[55;1H
[1;56r[55;1H#ls_recurse_enable=YES[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# When "listen" directive is enabled, vsftpd runs in standalone mode and[1;55r[55;1H
[1;56r[55;1H# listens on IPv4 sockets. This directive cannot be used in conjunction[1;55r[55;1H
[1;56r[55;1H# with the listen_ipv6 directive.[1;55r[55;1H
[1;56r[55;1Hlisten=NO[1;55r[55;1H
[1;56r[55;1H#[1;55r[55;1H
[1;56r[55;1H# This directive enables listening on IPv6 sockets. By default, listening[1;55r[55;1H
[1;56r[55;1H# on the IPv6 "any" address (::) will accept connections from both IPv6[1;55r[55;1H
[1;56r[55;1H# and IPv4 clients. It is not necessary to listen on *both* IPv4 and IPv6[1;55r[55;1H
[1;56r[55;1H# sockets. If you want that (perhaps because you want to listen on specific[1;55r[55;1H
[1;56r[55;1H# addresses) then you must run two copies of vsftpd with two configuration[1;55r[55;1H
[1;56r[55;1H# files.[1;55r[55;1H
[1;56r[55;1H# Make sure, that one of the listen options is commented !![1;55r[55;1H
[1;56r[55;1Hlisten_ipv6=YES[1;55r[55;1H
[1;56r[55;1H[1;55r[55;1H
[1;56r[55;1Hpam_service_name=vsftpd[1;55r[55;1H
[1;56r[55;1Huserlist_enable=YES[1;55r[55;1H
[1;56r[55;1Htcp_wrappers=YEStcp_wrappers=YE
[1m-- INSERT --[55;16H[1;55r[0m[55;1H
[1;56r[54;16H[K[55;1HS[56;1H[K[55;1H[54;1Htcp_wrappers=Y

[1m-- INSERT --[54;15H[0mEs[54;16H[K[54;16HS[55;1H[K[55;1Hanonymous_enable=YES[56;1H[K[55;20H
:wq"vsftpd.conf" 129L, 5053C written[?1l>
[root@iZ2zebby2spwhc553j2avtZ vsftpd]# vi[K[Kecho "anon_upload_enable=YES">>vsftp.conf
[root@iZ2zebby2spwhc553j2avtZ vsftpd]# tail -10 vsftp.conf
anon_upload_enable=YES
[root@iZ2zebby2spwhc553j2avtZ vsftpd]# 