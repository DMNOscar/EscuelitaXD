<?php
	require_once(dirname(__FILE__)."/../../core/utils.php");
	require_once(dirname(__FILE__)."/../../library/nusoap/lib/nusoap.php");
	
	$bd = connectBD();
	$id = $_REQUEST['id'];
	$usuarioID = $_REQUEST['nombre'];
	$nick 	 = $_REQUEST['nick'];
	$pass    = $_REQUEST['pass'];
	$categoria_id = 1;
	
	//Encriptamos el pass
	$cliente = new nusoap_client("http://atc.mx/android/libraryPHP/encriptado/seguridad.php?wsdl", true);
	$parametros = array ('password' => $pass);
	$passEncriptado = $cliente -> call('encriptar', $parametros);
	
	
	$consulta= "UPDATE usuario SET nombre='".$usuarioID."', nick='".$nick."', pass='".$passEncriptado."', categoria_id='".$categoria_id."'WHERE id='".$id."';";
				

	$resultado = $bd->query($consulta);
	
	if($resultado){
			echo "registrado";
	}
	
?>