<?php

	require_once(dirname(__FILE__)."/../../core/utils.php");

	$usuario = $_REQUEST['usuario'];
	$bd = connectBD();
	
	$consulta="SELECT e.id AS evaluacion_id, m.nombre , m.id AS materia_id, m.clave, e.calificacion, m.activo
				FROM evaluacion e  INNER JOIN materia m ON m.id = e.materia_id 
				INNER JOIN usuario u ON e.usuario_id= u.id
				WHERE u.id ='".$usuario."'";
	
	$resultado = mysqli_query($bd, $consulta);
	
	$datos = array();
	
	foreach($resultado as $row){
		
		$datos[] = $row;
	}
	
	echo json_encode($datos)
?>