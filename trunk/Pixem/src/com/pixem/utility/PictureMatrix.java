/**
 *
 * Copyright (c) 2009-2011 Envision Mobile Ltd. All rights reserved.
 *
 * Other software and company names mentioned herein or used for developing
 * and/or running the Envision Mobile Ltd's software may be trademarks or
trade
 * names of their respective owners.
 *
 * Everything in the source code herein is owned by Envision Mobile Ltd.
 * The recipient of this source code hereby acknowledges and agrees that
such
 * information is proprietary to Envision Mobile Ltd. and shall not be used,

 * disclosed, duplicated, and/or reversed engineered except in accordance
 * with the express written authorization of Envision Mobile Ltd.
 *
 * Module: Matrix.java
 * Project: PixemMain
 *
 * Description:
 *
 *
 * Developer:   10107896
 * Date:        2011-08-21
 * Version:
 *
 *
 */
package com.pixem.utility;

/**
* @author 10107896
*
*/
public class PictureMatrix {

	    private int matrixSize;
	    public double[][] matrix;
	    public int factor = 0, offset = 0;

	    public PictureMatrix() {
	        this(3);
	    }

	    public PictureMatrix(int size) {
	        matrixSize = size;
	        matrix = new double[matrixSize][matrixSize];
	    }

	    public void setValues(double value) {
	        for (int i = 0; i < matrixSize; i++) {
	            for (int j = 0; j < matrixSize; j++) {
	                matrix[i][j] = value;
	            }
	        }
	    }

	    public void setMiddle(int value) {

	        matrix[(matrixSize / 2)][(matrixSize / 2)] = value;

	        //matrix [1][1] = value;
	        //factor = value + ((matrixSize * matrixSize) - 1);

	        for (int i = 0; i < matrixSize; i++) {
	            for (int j = 0; j < matrixSize; j++) {
	                factor += matrix[i][j];
	            }
	        }

	        factor = value + 8;
	    }

	    public void setFactor(int value) {
	        factor = value;
	    }

	    public int getMatrixSize() {
	        return matrixSize;
	    }

	    public void setGaussian() {

	        /*
	         * assuming a 3x3 matrix to look like the following:
	         *    1   2   1
	         *    2   4   2
	         *    1   2   1
	         */
	        for (int i = 0; i < matrixSize; i++) {
	            for (int j = 0; j < matrixSize; j++) {

	                //center square
	                if ((i % 2 != 0) && (j % 2 != 0)) {
	                    matrix[i][j] = 4;
	                } else if ((i % 2 != 0) && (j % 2 == 0)) {
	                    matrix[i][j] = 2;
	                } else if ((i % 2 == 0) && (j % 2 != 0)) {
	                    matrix[i][j] = 2;
	                } else {
	                    matrix[i][j] = 1;
	                }

	                factor += matrix[i][j];
	                offset = 0;
	            }
	        }

	    }
}
