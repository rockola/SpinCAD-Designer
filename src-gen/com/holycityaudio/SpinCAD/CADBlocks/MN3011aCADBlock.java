/* SpinCAD Designer - DSP Development Tool for the Spin FV-1 
 * MN3011aCADBlock.java
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
		package com.holycityaudio.SpinCAD.CADBlocks;
	
		import com.holycityaudio.SpinCAD.SpinCADBlock;
		import com.holycityaudio.SpinCAD.SpinCADPin;
		import com.holycityaudio.SpinCAD.SpinFXBlock;
 		import com.holycityaudio.SpinCAD.ControlPanel.MN3011aControlPanel;
		
		public class MN3011aCADBlock extends SpinCADBlock {

			private static final long serialVersionUID = 1L;
			private MN3011aControlPanel cp = null;
			
			private double inputGain = 0.45;
			private double delayLength = 32767;
			private double tap1Ratio = 0.118990385;
			private double tap1Gain = 0.5;
			private double tap2Ratio = 0.198918269;
			private double tap2Gain = 0.5;
			private double tap3Ratio = 0.358774038;
			private double tap3Gain = 0.5;
			private double tap4Ratio = 0.518629808;
			private double tap4Gain = 0.5;
			private double tap5Ratio = 0.838341346;
			private double tap5Gain = 0.5;
			private double tap6Ratio = 1.0;
			private double tap6Gain = 0.5;
			private double delayOffset = -1;
			private int mix;
			private int max;
			private int output5;

			public MN3011aCADBlock(int x, int y) {
				super(x, y);
				setName("MN3011a");	
				// Iterate through pin definitions and allocate or assign as needed
				addInputPin(this, "Input");
				addOutputPin(this, "Mix_Out");
				addOutputPin(this, "Delay_Out_End");
				addControlInputPin(this, "Delay_Time_1");
			// if any control panel elements declared, set hasControlPanel to true
						hasControlPanel = true;
						hasControlPanel = true;
						hasControlPanel = true;
						hasControlPanel = true;
						hasControlPanel = true;
						hasControlPanel = true;
						hasControlPanel = true;
						hasControlPanel = true;
						}
		
			// In the event there are parameters editable by control panel
			public void editBlock(){ 
				if(cp == null) {
					if(hasControlPanel == true) {
						cp = new MN3011aControlPanel(this);
					}
				}
			}
			
			public void clearCP() {
				cp = null;
			}	
				
			public void generateCode(SpinFXBlock sfxb) {
	
			// Iterate through mem and equ statements, allocate accordingly

			int ratio = 1;
			
			sfxb.comment(getName());
			
			SpinCADPin sp = null;
					
			// Iterate through pin definitions and connect or assign as needed
			sp = this.getPin("Input").getPinConnection();
			int adcl = -1;
			if(sp != null) {
				adcl = sp.getRegister();
			}
			
			sp = this.getPin("Delay_Time_1").getPinConnection();
			int cIn1 = -1;
			if(sp != null) {
				cIn1 = sp.getRegister();
			}
			
			
			// finally, generate the instructions
			int	delayOffset = sfxb.getDelayMemAllocated() + 1;
			sfxb.FXallocDelayMem("mn3011delay", delayLength); 
			if(this.getPin("Input").isConnected() == true) {
			sfxb.readRegister(adcl, inputGain);
			sfxb.FXwriteDelay("mn3011delay", 0, 0.0);
			mix = sfxb.allocateReg();
			if(this.getPin("Delay_Time_1").isConnected() == true) {
			max = sfxb.allocateReg();
			sfxb.clear();
			sfxb.or(0x7FFF00);
			sfxb.mulx(cIn1);
			sfxb.writeRegister(max, 1.0);
			sfxb.scaleOffset((0.95 * tap1Ratio * delayLength)/32768.0, (delayOffset + (0.05 * tap1Ratio * delayLength))/32768.0);
			sfxb.writeRegister(ADDR_PTR, 0);
			sfxb.readDelayPointer(tap1Gain);
			sfxb.writeRegister(mix, 0.0);
			sfxb.readRegister(max, 1.0);
			sfxb.scaleOffset((0.95 * tap2Ratio * delayLength)/32768.0, (delayOffset + (0.05 * tap2Ratio * delayLength))/32768.0);
			sfxb.writeRegister(ADDR_PTR, 0);
			sfxb.readDelayPointer(tap2Gain);
			sfxb.readRegister(mix, 1.0);
			sfxb.writeRegister(mix, 0.0);
			sfxb.readRegister(max, 1.0);
			sfxb.scaleOffset((0.95 * tap3Ratio * delayLength)/32768.0, (delayOffset + (0.05 * tap3Ratio * delayLength))/32768.0);
			sfxb.writeRegister(ADDR_PTR, 0);
			sfxb.readDelayPointer(tap3Gain);
			sfxb.readRegister(mix, 1.0);
			sfxb.writeRegister(mix, 0.0);
			sfxb.readRegister(max, 1.0);
			sfxb.scaleOffset((0.95 * tap4Ratio * delayLength)/32768.0, (delayOffset + (0.05 * tap4Ratio * delayLength))/32768.0);
			sfxb.writeRegister(ADDR_PTR, 0);
			sfxb.readDelayPointer(tap4Gain);
			sfxb.readRegister(mix, 1.0);
			sfxb.writeRegister(mix, 0.0);
			sfxb.readRegister(max, 1.0);
			sfxb.scaleOffset((0.95 * tap5Ratio * delayLength)/32768.0, (delayOffset + (0.05 * tap5Ratio * delayLength))/32768.0);
			sfxb.writeRegister(ADDR_PTR, 0);
			sfxb.readDelayPointer(tap5Gain);
			sfxb.readRegister(mix, 1.0);
			sfxb.writeRegister(mix, 0.0);
			sfxb.readRegister(max, 1.0);
			sfxb.scaleOffset((0.95 * tap6Ratio * delayLength)/32768.0, (delayOffset + (0.05 * tap6Ratio * delayLength))/32768.0);
			sfxb.writeRegister(ADDR_PTR, 0);
			sfxb.readDelayPointer(tap6Gain);
			sfxb.readRegister(mix, 1.0);
			} else {
			ratio = (int) (tap1Ratio * delayLength);
			sfxb.FXreadDelay("mn3011delay+", ratio, tap1Gain);
			ratio = (int) (tap2Ratio * delayLength);
			sfxb.FXreadDelay("mn3011delay+", ratio, tap2Gain);
			ratio = (int) (tap3Ratio * delayLength);
			sfxb.FXreadDelay("mn3011delay+", ratio, tap3Gain);
			ratio = (int) (tap4Ratio * delayLength);
			sfxb.FXreadDelay("mn3011delay+", ratio, tap4Gain);
			ratio = (int) (tap5Ratio * delayLength);
			sfxb.FXreadDelay("mn3011delay+", ratio, tap5Gain);
			ratio = (int) (tap6Ratio * delayLength);
			sfxb.FXreadDelay("mn3011delay+", ratio, tap6Gain);
			}
			
			sfxb.writeRegister(mix, 0.0);
			this.getPin("Mix_Out").setRegister(mix);
			output5 = sfxb.allocateReg();
			sfxb.FXreadDelay("mn3011delay#", 0, 1.0);
			sfxb.writeRegister(output5, 0.0);
			this.getPin("Delay_Out_End").setRegister(output5);
			}
			

			}
			
			// create setters and getter for control panel variables
			public void setinputGain(double __param) {
				inputGain = __param;	
			}
			
			public double getinputGain() {
				return inputGain;
			}
			public void setdelayLength(double __param) {
				delayLength = __param;	
			}
			
			public double getdelayLength() {
				return delayLength;
			}
			public void settap1Gain(double __param) {
				tap1Gain = __param;	
			}
			
			public double gettap1Gain() {
				return tap1Gain;
			}
			public void settap2Gain(double __param) {
				tap2Gain = __param;	
			}
			
			public double gettap2Gain() {
				return tap2Gain;
			}
			public void settap3Gain(double __param) {
				tap3Gain = __param;	
			}
			
			public double gettap3Gain() {
				return tap3Gain;
			}
			public void settap4Gain(double __param) {
				tap4Gain = __param;	
			}
			
			public double gettap4Gain() {
				return tap4Gain;
			}
			public void settap5Gain(double __param) {
				tap5Gain = __param;	
			}
			
			public double gettap5Gain() {
				return tap5Gain;
			}
			public void settap6Gain(double __param) {
				tap6Gain = __param;	
			}
			
			public double gettap6Gain() {
				return tap6Gain;
			}
		}	
