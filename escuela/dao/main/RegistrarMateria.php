<?php
	require_once(dirname(__FILE__)."/../../core/utils.php");
	$bd = connectBD();
	$nombre = $_REQUEST['nombre'];
	$clave 	 = $_REQUEST['clave'];
	

	
	$consulta= "INSERT INTO materia(clave, nombre) VALUES ('".$clave."','".$nombre."');";
				

	$resultado = $bd->query($consulta);
	if($resultado){
			echo "registrado";
	}
	
?>