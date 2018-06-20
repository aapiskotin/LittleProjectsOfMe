#!/usr/bin/env perl

use 5.016;
use strict;
use warnings;
use Getopt::Long qw(:config no_ignore_case);
use List::Util qw(sum max min);

my ($count, $ignore, $inverse, $fixed, $numeric);
my $after = 0;
my $before = 0;
my $context = 0;
GetOptions ('A=i' => \$after, 'B=i' => \$before, 'C=i' => \$context, 'c' => \$count,
			'i' => \$ignore, 'v' => \$inverse, 'F' => \$fixed, 'n' => \$numeric)
				or die "Bad options";
my $pattern = $ARGV[-1];
$pattern = quotemeta($pattern) if $fixed;
$pattern = "(?i)$pattern" if $ignore;
my @before_context;
if ($context) {
	$after = $context;
	$before = $context;
}

my $counter = 0;
my $num_of_lines = 0;
if ($count) {
	while (my $line = <STDIN>) {
		$num_of_lines ++;
		$counter ++ if ($line =~ /$pattern/);
	}
	if ($inverse) {
		say $num_of_lines - $counter;
	}
	else {
		say $counter;
	}
	exit(0);
}

my $cur_line_num = 0;
my $after_flag = 0;
my $last_matched_line = 0;
while (my $line = <STDIN>) {
	chomp $line;
	$cur_line_num ++;
	if ($before) {
		push @before_context, $line;
		shift @before_context if (@before_context - 1) > $before;
	}
	if ($line =~ /$pattern/ && !$inverse or $line !~ /$pattern/ && $inverse) {
		if ($before) {
			say "--" if ($cur_line_num - $before) > 1 and ($cur_line_num - $last_matched_line) > 1 ;
			my $j = 0;
			while ($j < $#before_context) {
				if ($cur_line_num - $#before_context + $j > $last_matched_line) {
					printf("%i-", ($cur_line_num - $#before_context + $j)) if $numeric;
					say $before_context[$j];
				}
				$j ++;
			}
		}
		print ("$cur_line_num:") if $numeric;
		say $line;
		$after_flag = $after;
		$last_matched_line = $cur_line_num;
	}
	elsif ($after_flag) {
		print "$cur_line_num-" if $numeric;
		say "$line";
		$after_flag --;
		say "--" if not $after_flag and not eof;
	}
}
