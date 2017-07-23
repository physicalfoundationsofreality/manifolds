package com.gruber.pfr.space.vectors.linearmaps;

import com.gruber.pfr.space.rings.Ring;
import com.gruber.pfr.space.rings.RingElement;

public class FiniteMatrix implements Matrix {

	RingElement[][] matrix;
	Ring baseRing;
	FiniteMatrix colNormForm;
	FiniteMatrix rowNormForm;
	FiniteMatrix colNormalizer;
	FiniteMatrix rowNormalizer;
	int rank = 0;

	public FiniteMatrix(Ring baseRing, RingElement[][] matrix) {
		super();
		this.matrix = matrix;
		this.baseRing = baseRing;
	}

	public RingElement[] multiply(RingElement[] vec) {

		if (vec.length == 0)
			return vec;

		if (matrix[0].length != vec.length)
			return null;

		RingElement[] im = new RingElement[matrix.length];
		for (int i = 0; i < im.length; i++) {

			im[i] = (RingElement) this.baseRing.getNullElement();
			for (int j = 0; j < vec.length; j++)
				im[i] = im[i].add(matrix[i][j].multiply(vec[j]));
		}
		return im;
	}

	public RingElement[][] getMatrix() {
		return matrix;
	}

	public Ring getBaseRing() {
		return baseRing;
	}

	public void exchangeRows(int i, int j) {

		RingElement[] rowi = matrix[i];
		matrix[i] = matrix[j];
		matrix[j] = rowi;
	}

	public int getColumnNumber() {

		if (this.matrix.length == 0)
			return 0;
		else
			return this.matrix[0].length;
	}

	public int getRownNumber() {

		return this.matrix.length;
	}

	/*
	 * columns normal diagonal form for Matrix with Rank r: all columns index >
	 * r are 0, top left corner r-dim unit matrix
	 */
	public FiniteMatrix getColumnsDiaginalNormalizedForm() {

		if (this.colNormForm == null)
			this.diagonolize();

		return this.colNormForm;
	}
	
	public static RingElement[][] copyOf(RingElement[][] matrix) {
		
		if(matrix.length == 0)
			return new RingElement[0][0];
		
		RingElement[][] ret = new RingElement[matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				ret[i][j] = (RingElement)matrix[i][j].clone();
		
		return ret;
	}

	protected void diagonolize() {

		int dimCol = this.getColumnNumber();
		int dimRow = this.getRownNumber();
//		int dimMatrix = Math.min(dimCol, dimRow);
		
		int colPos = 0;
		int rowPos = 0;

		RingElement nullEl = (RingElement) this.baseRing.getNullElement();
		RingElement oneEl = (RingElement) this.baseRing.getOneElement();

		RingElement[][] colNormalForm = copyOf(matrix);
		RingElement[][] rowNormalForm = copyOf(matrix);
		RingElement[][] colNormal = new RingElement[dimCol][dimCol];
		RingElement[][] rowNormal = new RingElement[dimRow][dimRow];

		for (int i = 0; i < dimCol; i++)
			for (int j = 0; j < dimCol; j++)
				if (i == j)
					colNormal[i][j] = oneEl;
				else
					colNormal[i][j] = nullEl;

		for (int i = 0; i < dimRow; i++)
			for (int j = 0; j < dimRow; j++)
				if (i == j)
					rowNormal[i][j] = oneEl;
				else
					rowNormal[i][j] = nullEl;

		this.colNormalizer = new FiniteMatrix(baseRing, colNormal);
		this.rowNormalizer = new FiniteMatrix(baseRing, rowNormal);
		this.colNormForm = new FiniteMatrix(baseRing, colNormalForm);
		this.rowNormForm = new FiniteMatrix(baseRing, rowNormalForm);

		while (colPos < dimCol && rowPos < dimRow) {

			// find a remaining Vector with map factor not 0
			for (int i = rowPos; i < dimRow; i++) {
				if (!this.rowNormForm.getElement(i,colPos).equals(nullEl)) {
					this.rowNormalizer.exchangeRows(rowPos, i);
					this.rowNormForm.exchangeRows(rowPos, i);
					break;
				}
			}
			if (this.rowNormForm.getElement(rowPos, colPos).equals(nullEl)) {
				colPos++;
				continue;
			}

			RingElement factor = rowNormForm.getElement(rowPos, colPos).getInverse();
			this.rowNormForm.scaleRow(rowPos, factor);
			this.rowNormalizer.scaleRow(rowPos, factor);

			rank++;

			// adapt the rest of the vectors and matrix rows to to factor 0 at
			// rowPpos
			for (int i = 0; i < dimRow; i++) {
				if (i != rowPos) {
					RingElement multiplier = rowNormForm.getElement(i, colPos).getNegative();
					this.rowNormForm.addRow(i, rowPos, multiplier);
					this.rowNormalizer.addRow(i, rowPos, multiplier);
				}
			}

			rowPos++;
			colPos++;
		}

		colPos = 0;
		rowPos = 0;
		
		while (colPos < dimCol && rowPos < dimRow) {

			// find a remaining Vector with map factor not 0
			for (int i = colPos; i < dimCol; i++) {
				if (!this.colNormForm.getElement(rowPos, i).equals(nullEl)) {
					this.colNormalizer.exchangeColumns(i, colPos);
					this.colNormForm.exchangeColumns(i, colPos);
					break;
				}
			}
			if (this.colNormForm.getElement(rowPos, colPos).equals(nullEl)) {
				rowPos++;
				continue;
			}

			RingElement factor = colNormForm.getElement(rowPos, colPos).getInverse();
			this.colNormForm.scaleColumn(colPos, factor);
			this.colNormalizer.scaleColumn(colPos, factor);

			// adapt the rest of the vectors and matrix rows to to factor 0 at
			// rowPpos
			for (int i = 0; i < dimCol; i++) {
				if (i != colPos) {
					RingElement multiplier = colNormForm.getElement(rowPos, i).getNegative();
					this.colNormForm.addColumn(i, colPos, multiplier);
					this.colNormalizer.addColumn(i, colPos, multiplier);
				}
			}

			rowPos++;
			colPos++;
		}
	}

	/*
	 * rows normal diagonal form for Matrix with Rank r: all rows index > r are
	 * 0, top left corner r-dim unit matrix
	 */
	public FiniteMatrix getRowsDiaginalNormalizedForm() {

		if (this.rowNormForm == null)
			this.diagonolize();

		return this.rowNormForm;
	}

	public FiniteMatrix getColumnsNormalizer() {

		if (this.colNormalizer == null)
			this.diagonolize();

		return this.colNormalizer;
	}

	public FiniteMatrix getRowsNormalizer() {

		if (this.rowNormalizer == null)
			this.diagonolize();

		return this.rowNormalizer;
	}

	public void exchangeColumns(int i, int j) {

		RingElement[] coli = new RingElement[matrix.length];
		for (int k = 0; k < matrix.length; k++)
			coli[k] = matrix[k][i];

		for (int k = 0; k < matrix.length; k++)
			matrix[k][i] = matrix[k][j];

		for (int k = 0; k < matrix.length; k++)
			matrix[k][j] = coli[k];
	}

	public void scaleRow(int i, RingElement scalar) {

		for (int k = 0; k < matrix.length; k++)
			matrix[i][k] = scalar.multiply(matrix[i][k]);
	}

	public void scaleColumn(int i, RingElement scalar) {

		for (int k = 0; k < matrix.length; k++)
			matrix[k][i] = scalar.multiply(matrix[k][i]);
	}

	public Matrix getTransposed() {

		RingElement[][] trans = new RingElement[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				trans[j][i] = matrix[i][j];

		return new FiniteMatrix(this.baseRing, trans);
	}

	public void addRow(int row, int addedRow, RingElement scalar) {

		for (int k = 0; k < matrix[row].length; k++)
			matrix[row][k] = matrix[row][k].add(scalar.multiply(matrix[addedRow][k]));
	}

	public void addColumn(int col, int addedCol, RingElement scalar) {

		for (int k = 0; k < matrix.length; k++)
			matrix[k][col] = matrix[k][col].add(scalar.multiply(matrix[k][addedCol]));
	}

	public RingElement getElement(int i, int j) {
		return this.matrix[i][j];
	}

	public Matrix getInverse() {

		if (this.rowNormalizer == null)
			this.diagonolize();

		if (rank < this.getColumnNumber() || rank < this.getRownNumber())
			return null;
		else
			return this.colNormalizer;
	}

	public int getRank() {

		if (this.rowNormalizer == null)
			this.diagonolize();

		return rank;
	}

	public Matrix multiply(Matrix rightMultiplicant) {
		
		FiniteMatrix multi = (FiniteMatrix)rightMultiplicant;
		
		RingElement[][] mult = new RingElement[this.getRownNumber()][multi.getColumnNumber()];
		for(int i = 0; i < mult.length; i++)
			for(int j = 0; j < multi.getColumnNumber(); j++) {
				mult[i][j] = (RingElement)this.baseRing.getNullElement();
				for(int k = 0; k < this.getColumnNumber(); k++)
					mult[i][j] = mult[i][j].add(this.matrix[i][k].multiply(multi.getElement(k, j)));
			}
		return new FiniteMatrix(this.baseRing,mult);
	}
}
