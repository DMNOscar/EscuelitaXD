<?php

	require_once(dirname(__FILE__)."/../../core/utils.php");

	$bd = connectBD();
	$consulta="SELECT * FROM materia";
	
	$resultado = mysqli_query($bd, $consulta);
	
	$datos = array();
	
	foreach($resultado as $row){
		
		$datos[] = $row;
	}
	
	echo json_encode($datos)
?>