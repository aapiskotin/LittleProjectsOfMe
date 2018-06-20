#!/usr/bin/env perl

use 5.016;
use warnings;
use Time::Local 'timelocal';
use Scalar::Util 'looks_like_number';

my $month;
my ($sec,$min,$hour,$mday,$mon,$year,$wday,$yday,$isdst) =
    localtime(time);

if (@ARGV == 1) {
    ($month) = @ARGV;

    die "Not a number" unless looks_like_number($month);
    die "Bad arguments" if
        ( $month - sprintf("%d",$month) || $month < 1 || $month > 12);

    $month --;
}
elsif (not @ARGV) {
    $month = $mon;
}

my $time;
if ($month != 11) {
    $time = timelocal(1,1,1,1,$month + 1,$year);
}
else {
    $time = timelocal(1,1,1,1,0,$year + 1);
}
$time = $time - 60*60*24;
my $last_mday;
($sec,$min,$hour,$last_mday,$mon,$year,$wday,$yday,$isdst) =
    localtime($time);

$time = timelocal(1,1,1,1,$month,$year);
my $first_wday;
($sec,$min,$hour,$mday,$mon,$year,$first_wday,$yday,$isdst) =
    localtime($time);

$first_wday = 7 if $first_wday == 0;
my $indent = "";
for my $i (1..($first_wday - 1)) {
    $indent = "$indent   ";
}
my $i = $first_wday;
my $days = "";
for my $k (1..$last_mday) {
    $days = sprintf("%s%2d ",$days, $k);
    $i ++;
    if ($i > 7) {
        $days = "$days\n";
        $i = 1;
    }
}

my @months = ('January', 'February', 'March', 'April',
                'May', 'June', 'July', 'August',
                'Septemer', 'October', 'November', 'December');
printf("\t%s 2018\nMo Tu We Th Fr Sa Su\n%s%s\n", $months[$month], $indent, $days);
