<?php

	require_once(dirname(__FILE__)."/../../core/utils.php");
	require_once(dirname(__FILE__)."/../../library/nusoap/lib/nusoap.php");
	
	$bd = connectBD();
	
	$nick= $_REQUEST['nick'];
	$pass= $_REQUEST['pass'];
	
	//Encriptamos el pass
	$cliente = new nusoap_client("http://atc.mx/android/libraryPHP/encriptado/seguridad.php?wsdl", true);
	$parametros = array ('password' => $pass);
	$passEncriptado = $cliente -> call('encriptar', $parametros);
	
	//Realizamos la consulta
	
	$consulta = "SELECT u.id AS usuarioid, c.id AS categoriaid,u.nombre, u.activo, c.nombre AS categoria
	FROM usuario u
	INNER JOIN categoria c ON c.id = u.categoria_id
	WHERE nick = '".$nick."'
	AND pass = '".$passEncriptado."'";
	
	$resultado = mysqli_query($bd, $consulta);
	
	$datos = array();
	
	foreach($resultado as $row){
		
		$datos[] = $row;
	}
	
	echo json_encode($datos);
	
?>