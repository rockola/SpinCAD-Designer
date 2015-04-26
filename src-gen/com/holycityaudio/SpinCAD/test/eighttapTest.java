/* SpinCAD Designer - DSP Development Tool for the Spin FV-1 
 * eighttapTest.java
 * Copyright (C) 2015 - Gary Worsham 
 * Based on ElmGen by Andrew Kilpatrick 
 * 
 *   This program is free software: you can redistribute it and/or modify 
 *   it under the terms of the GNU General Public License as published by 
 *   the Free Software Foundation, either version 3 of the License, or 
 *   (at your option) any later version. 
 * 
 *   This program is distributed in the hope that it will be useful, 
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 *   GNU General Public License for more details. 
 * 
 *   You should have received a copy of the GNU General Public License 
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 *     
 */ 
 
package com.holycityaudio.SpinCAD.test;

import com.holycityaudio.SpinCAD.SpinCADFrame;
import com.holycityaudio.SpinCAD.SpinCADPanel;
import com.holycityaudio.SpinCAD.SpinCADPin;
import com.holycityaudio.SpinCAD.CADBlocks.*;

public class eighttapTest {
	public eighttapTest(SpinCADFrame f) {
		System.out.println("Start of eighttapCADBlock unit test..." + "\n");

		SpinCADPanel p = new SpinCADPanel(f);
		InputCADBlock i = new InputCADBlock(225, 10);
		OutputCADBlock o = new OutputCADBlock(225, 290);
		eighttapCADBlock m = new eighttapCADBlock(225, 210);
f.dropBlock(p, m);	

f.getModel().sortAlignGen();
System.out.println("eighttapCADBlock test passed with no connections!");
f.getModel().newModel();
// now do "all control connections"
f.dropBlock(p, i);	
f.dropBlock(p, m);	
f.dropBlock(p, o);	
{
	SpinCADPin p1 = i.getPin("Output 1");
	SpinCADPin p2 = m.getPin("Input");
	p2.setConnection(i, p1);
}
{
	SpinCADPin p1 = i.getPin("Output 2");
	SpinCADPin p2 = m.getPin("Feedback");
	p2.setConnection(i, p1);
}
{
	SpinCADPin p1 = o.getPin("Input 1");
	SpinCADPin p2 = m.getPin("Mix 1 Out");
	p2.setConnection(o, p1);
}
{
	SpinCADPin p1 = o.getPin("Input 2");
	SpinCADPin p2 = m.getPin("Mix 2 Out");
	p2.setConnection(o, p1);
}
{
	SpinCADPin p1 = o.getPin("Input 3");
	SpinCADPin p2 = m.getPin("Tap 8 Out");
	p2.setConnection(o, p1);
}
{
	Pot0CADBlock pot0 = new Pot0CADBlock(25, 150 + 40 * 1);
	f.dropBlock(p, pot0);
	SpinCADPin p1 = pot0.getPin("Output 1");
	SpinCADPin p2 = m.getPin("Delay Time 1");
	p2.setConnection(pot0, p1);
}
{
	Pot1CADBlock pot1 = new Pot1CADBlock(25, 150 + 40 * 2);
	f.dropBlock(p, pot1);
	SpinCADPin p1 = pot1.getPin("Output 1");
	SpinCADPin p2 = m.getPin("Feedback Gain");
	p2.setConnection(pot1, p1);
}
f.getModel().sortAlignGen();
System.out.println("eighttapCADBlock test passed with all control connections!");

f.getModel().newModel();	

// end of test code
	System.out.println("End of eighttapCADBlock unit test" + "\n");
	}
}
