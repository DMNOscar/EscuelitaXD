<?php
	require_once(dirname(__FILE__)."/../../core/utils.php");
	$bd = connectBD();
	$calificacion = $_REQUEST['calificacion'];
	$usuarioID = $_REQUEST['usuarioID'];
	$materiaID = $_REQUEST['materiaID'];
	

	
	$consulta= "INSERT INTO evaluacion(calificacion, usuario_id,materia_id) VALUES ('".$calificacion."','".$usuarioID."','".$materiaID."');";
				

	$resultado = $bd->query($consulta);
	if($resultado){
			echo "registrado";
	}
	
?>