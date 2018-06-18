<?php

	require ("config.php");
	
	function connectBD(){
	
		$bd = new mysqli(BD_HOST, BD_USER, BD_PASS,BD_NAME);
		
		IF ($bd ->connect_error){
			die();
		}else {
		
			$bd->set_charset("utf8");
			return $bd;
		
		}
	}
	
?>