#!/usr/bin/env perl

use 5.016;
use warnings;
use strict;
use Scalar::Util 'looks_like_number';

die "Bad arguments" if @ARGV != 1;
my $n = $ARGV[0];
die "Bad arguments" if
        ( !looks_like_number($n) || $n - sprintf("%d", $n) || $n < 1);

if ($n == 1) {
    say "There is no prime numbers";
}
else {
    say "Prime numbers from 1 to $n:";
    for my $cur_num (2 .. $n) {
        my $divider = 2;
        while ($cur_num % $divider != 0 && $divider <= $cur_num) {
            $divider ++;
        }
        say $cur_num if $divider == $cur_num;
    }
}
