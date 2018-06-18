<?php

	require_once(dirname(__FILE__)."/../../core/utils.php");
	
	$bd = connectBD();

	$consulta = "SELECT u.id AS usuario_id,u.nombre AS usuario_nombre,u.nick,u.pass,ca.id AS categoria_id,ca.nombre AS categoria_nombre,ca.activo FROM usuario u INNER JOIN categoria ca ON ca.id = u.categoria_id WHERE ca.nombre = 'Alumno'";
	$resultado = $bd->query($consulta);
	$datos =array();
	
	foreach ($resultado as $row) {
		$datos[]=$row;
	}
	
	echo json_encode($datos);


?>