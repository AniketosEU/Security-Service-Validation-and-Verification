/**
 * Copyright 2014 CNR <http://www.iit.cnr.it/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * Artsiom Yautsiukhin <artsiom.yautsiukhin@iit.cnr.it>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package iit.main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//import security.iit.pdp.PDP;
//import security.iit.pdp.PDP.WHEN;
import eu.aniketos.spec.Specification;


public class ConSpec_Main {

	/**
	 * Example of PDP call
	 * @param args
	 */
	public static void main(String[] args) {
		Specification spec = new Specification();
		try {
			
			//first of all we load the policy from a file, there are 3 ways
			//Option 1: from path string
			//spec.load("rsc\\spec_ret.xml");
			
			//Option 2: from byte array
			//File file = new File("rsc\\SoD_Goal.xml");
			File file = new File("rsc\\TestR.xml");
			byte[] bytes = saveToByteArray(file);
			spec.load(bytes);
			
			spec.save("rsc\\Test2R.xml");
			
			System.out.println("finished");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
    private static byte[] saveToByteArray(File jar) throws IOException {
        byte[] fileBArray = new byte[(int)jar.length()];
        FileInputStream fis = new FileInputStream(jar);
        fis.read(fileBArray);
        fis.close();
        return fileBArray;
    }

}
