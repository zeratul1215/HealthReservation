// HelloPoint1.js (c) 2012 matsuda
// Vertex shader program
/*
// MODIFIED J. Tumblin 1/2017  to make 'HelloMatrixDegen.js'. 
// MODIFIED J. Tumblin 1/2017 to make 'HelloQuaternion.js' 

Simple program to test basic quaternion operations using the 'Quaternion'
objects and functions found in ../lib/cuon-matrix-quat03.js

--Includes code to encourage exploring basic vector/matrix operations;
-- Demonstrate that matrices have finite precision, and thus contain tiny errors that can accumulate. THUS you should never write code that endlessly concatenates rotation-only matrices (e.g. an 'orientation' matrix made by continually re-applying rotation matrices), because eventually the result accumulates numerical errors that cause wholly unpredictable non-rotation transforms, including non-uniform scale, translation, shear, skew, and even unwanted projective distortions.  These matrices 'degenerate' -- they're no longer pure 3D  rotations!

-- Further code encourages exploring quaternion operations.

Nothing interesting happens in the canvas -- it's all in the console!
*/

var VSHADER_SOURCE = 
  'void main() {\n' +
  '  gl_Position = vec4(0.0, 0.0, 0.0, 1.0);\n' + // Set the vertex coordinates of the one and only point
  '  gl_PointSize = 10.0;\n' +                    // Set the point size. CAREFUL! MUST be float, not integer value!!
  '}\n';

// Fragment shader program
var FSHADER_SOURCE =
  'void main() {\n' +
  '  gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);\n' + // Set the point color
  '}\n';

function main() {
  // Retrieve <canvas> element
  var canvas = document.getElementById('webgl');

  // Get the rendering context for WebGL
  var gl = getWebGLContext(canvas);
  if (!gl) {
    console.log('Failed to get the rendering context for WebGL');
    return;
  }

  // Initialize shaders
  if (!initShaders(gl, VSHADER_SOURCE, FSHADER_SOURCE)) {
    console.log('Failed to intialize shaders.');
    return;
  }
  console.log('Hey! we have all our shaders initialized!');

  // Specify the color for clearing <canvas>
  gl.clearColor(0.0, 0.0, 0.0, 1.0);

  // Clear <canvas>
  gl.clear(gl.COLOR_BUFFER_BIT);

  // Draw a point
  gl.drawArrays(gl.POINTS, 0, 1);


  //============================================================
  // Lets play around with Vector4 objects:
  //============================================================

	
	
  //============================================================
	// Lets play around with Matrix4 objects
  //============================================================
  var aMat = new Matrix4();
	aMat.setIdentity();
	var mySiz = 3000;
	var count;
	
	console.log('Rotate aMat by (360/'+mySiz+') degrees\n around the (1,3,5) axis,'+mySiz+' times:');
	for(count = 0; count < mySiz; count++) {
			aMat.rotate(-360.0/mySiz, 1.0, 3.0, 5.0);
		}
		console.log('Result SHOULD be a perfect identity matrix, but it is not:');
		aMat.printMe();
		console.log('Instead, this degenerate matrix accumulated errors that');
		console.log('cause other, unpredictable, non-rotation transforms.  BAD!');
		console.log('THUS you should never use matrix multiplies to combine a');
		console.log('long series of rotations.  Instead, use quaternions.');
		console.log('NOTE: open the .js file and the HTML file; \n Plenty to explore, comment & uncomment!');

	//============================================================
	//  Let's play around with Quaternion objects
	//============================================================
	/* I found these Quaternion member functions:
				Constructor: Quaternion(x,y,z,w);
				clear();
				copy(q);
--> 		printMe();
-->			setFromAxisAngle(ax, ay, az, angleDeg);
				UNFINISHED: setFromEuler(AlphaDeg, BetaDeg, gammaDeg);
-->			setFromRotationMatrix(m);
				calculateW();
				inverse();
				length();
-->			normalize();
				multiplySelf(quat2);
-->			multiply(q1,q2);
				multiplyVector3(vec, dest);
				slerp(qa,ab,qm,t);
	I also found this Matrix4 member:
			setFromQuat(qx,qy,qz,qw);
	*/	
		



	testQuaternions();
}

function testQuaternions(){
	console.log("------------------------------------homework part--------------------------------")
	//vec4 part
	avecArray = [4.0,3.0,2.0,0.0];
	bvecArray = [1.0,2.0,3.0,0.0];
	var aVec = new Vector4(avecArray);
	var bVec = new Vector4(bvecArray);
	aVec[0] = 4.0; aVec[1] = 3.0; aVec[2] = 2.0; aVec[3] = 0.0;
	bVec[0] = 1.0; bVec[1] = 2.0; bVec[2] = 3.0; bVec[3] = 0.0;
	// x,y,z,w=0 (vector, not point)
	console.log('\n---------------Vector4 Ops------------\n');
	res = 3;		// number of digits we will print on console log
	tmp = aVec;	// (temporary var so we don't change aVec or bVec)
	console.log('aVec: x=', tmp[0].toFixed(res),
		'y=', tmp[1].toFixed(res),
		'z=', tmp[2].toFixed(res),
		'w=', tmp[3].toFixed(res),'\n');
	tmp = bVec;
	console.log('bVec: x=', tmp[0].toFixed(res),
		'y=', tmp[1].toFixed(res),
		'z=', tmp[2].toFixed(res),
		'w=', tmp[3].toFixed(res),'\n');


	console.log("length\n");
	function getVec4Length(vec){
		return Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1] + vec[2] * vec[2] + vec[3] * vec[3]);
	}
	console.log("aVec length:" + getVec4Length(aVec) + "\n");
	console.log("bVec length:" + getVec4Length(bVec) + "\n");

	console.log("dot\n");
	console.log("aVec * bVec: "+ aVec.dot(bVec));

	console.log("console\n");
	var cross = aVec.cross(bVec);
	console.log("aVec x bVec: "+ cross.elements[0] +"," + cross.elements[1] +","+ cross.elements[2] +","+ cross.elements[3]);


	//the quaternion part
	var q0 = new Quaternion();
	var q1 = new Quaternion();

	var R0 = new Matrix4();
	var R1 = new Matrix4();

	console.log('q0 made with empty constructor:');
	q0.printMe();
	console.log('convert this default q0 to matrix R0; makes identity matrix:');
	R0.setFromQuat(q0.x, q0.y, q0.z, q0.w);
	R0.printMe();
	console.log('YES! AGREES with online quaternion calculator!');
	console.log('set q0 to axis 2,0,0; +30deg.-----------------');
	console.log('Call setFromAxisAngle(2,0,0, 30.0) -- it always creates a UNIT quaternion:');
	q0.setFromAxisAngle(2,0,0, 30.0);
	q0.printMe();
	console.log('q0 length==',q0.length());
	console.log('convert q0 to matrix R0:');
	R0.setFromQuat(q0.x, q0.y, q0.z, q0.w);
	R0.printMe();
	console.log('YES! AGREES with online quaternion calculator!');
	console.log('set q1 to axis 0,0,0.2; -45deg.---------------');
	q1.setFromAxisAngle(0,0,0.2, -45.0);
	q1.printMe();
	console.log('q1 length==',q1.length());
	console.log('convert q1 to matrix R1:');
	R1.setFromQuat(q1.x, q1.y, q1.z, q1.w);
	R1.printMe();
	console.log('YES! AGREES with online quaternion calculator!');

	var q2 = new Quaternion();
	var R2 = new Matrix4();
	console.log('set q1 to axis 0,0,0.2; -45deg.---------------');
	q2.setFromAxisAngle(1,1,1, 90.0);
	q2.printMe();
	console.log('q2 length==',q2.length());
	console.log('convert q2 to matrix R2:');
	R2.setFromQuat(q2.x, q2.y, q2.z, q2.w);
	R2.printMe();
	console.log('YES! AGREES with online quaternion calculator!');

	var q00 = new Quaternion(1,2,3,4);
	var q11 = new Quaternion(4,3,2,1);
	q01 = q00.multiplySelf(q11);
	q10 = q11.multiplySelf(q00);
	q01.printMe();
	q10.printMe();
	console.log("q01 yields the rotation matrix matches M01\n" +
		"q10 matches with M10\n" +
		"because when we use the quaternions, the new rotation are placed on the left side of the multiplication.\n" +
		"and the same for the matrices.")

	//matrix4 part
	var Mat1 = new Matrix4();
	var Mat2 = new Matrix4();
	Mat1.elements[1] = 20;
	Mat2.elements[2] = 30;
	var R01 = Mat1.concat(Mat2);
	var R02 = Mat2.concat(Mat1);

	R01.printMe();
	R02.printMe();
}
