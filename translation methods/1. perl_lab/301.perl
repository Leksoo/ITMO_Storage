
$need_empty = 0;
$res = "";
while(<>) {
	if(/^\s*$/){
		if($need_empty == 1 ){
			$res = $res . "\n";
		}
        $need_empty = 0;
	} else{
		$need_empty = 1;
		s/^(\s*)(.+?)(\s*)$/$2/;
		s/(\s+)/ /g;
		$res = $res . "$_\n";
	}
}
$res =~ s/^(\s)*//;
$res =~ s/(\s)*$//;
print $res;