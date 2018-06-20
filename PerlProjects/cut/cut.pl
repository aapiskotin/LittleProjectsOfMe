#!/usr/bin/env perl

use 5.016;
use strict;
use warnings;
use Getopt::Long;
use DDP;

my ($delimiter, $fields_str, $separated);
GetOptions ('f=s' => \$fields_str,
			'd=s' => \$delimiter,
			's' => \$separated);

die "Can't run without -f option" unless $fields_str;
my @fields = split /,/, $fields_str;

my $qmed_delimiter;
if ($delimiter) {
	die "Bad delimiter" if length($delimiter) > 1;
	$qmed_delimiter = quotemeta($delimiter);
}
else {
	$delimiter = "\t";
	$qmed_delimiter = "\t";
}

while (my $line = <STDIN>) {
	chomp $line;
	my @line_columns = split /$qmed_delimiter/, $line;
	if ($separated) {
		if ($line =~ /$qmed_delimiter/) {
			output_line(\@fields, \@line_columns, $delimiter);
		}
	}
	else {
		output_line(\@fields, \@line_columns, $delimiter);
	}
}

sub output_line {
	my ($fields, $line_columns, $delimiter) = @_;
		
	my $output_line;
	for my $i (@{$fields}) {
		if (defined ${$line_columns}[$i-1]) {
			if (defined $output_line) {
				$output_line = join $delimiter, ($output_line, ${$line_columns}[$i-1]);
			}
			else {
				$output_line = ${$line_columns}[$i-1]
			}
		}
	}
	say $output_line;
}
