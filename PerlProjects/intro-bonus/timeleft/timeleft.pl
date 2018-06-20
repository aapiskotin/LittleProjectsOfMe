#!/usr/bin/env perl

use 5.016;
use warnings;
use strict;

my $time = time + 60*60*3;

say 60*60 - $time%(60*60);
say 60*60*24 - $time%(60*60*24);
say 60*60*24*7 - ($time - 60*60*24*4)%(60*60*24*7);
