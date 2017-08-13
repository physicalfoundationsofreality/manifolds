package com.gruber.pfr.space.vectors.linearmaps;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVector;
import com.gruber.pfr.space.vectors.affine.AffineSubspace;
import com.gruber.pfr.space.vectors.basis.FiniteDimensionalVectorSpaceBasis;
import com.gruber.pfr.space.vectors.basis.VectorSpan;

public class FiniteDimensionalLinearMap extends LinearMap {

	// phi(ej) = sum(mij * fi)
	// -> phi(sum(bj * ej)) = phi(bj * sum(mij * fi ))
	// -> phi(b) has coordinates bi = sum( mij bj )
	FiniteMatrix matrix;
	FiniteDimensionalVectorSpaceBasis domainBasis;
	FiniteDimensionalVectorSpaceBasis rangeBasis;
	VectorSpan kernel;
	VectorSpan image;
	VectorSpan coKernel; // there is no unique cokernel, we just choose one
	FiniteMatrix isoMatrix; // the matrix of the isomorphism between coKernel
							// and image
	FiniteDimensionalLinearMap isoMap; // The map from cokernel to kernel
	FiniteDimensionalLinearMap isoInverseMap;
	FiniteDimensionalLinearMap inverseMap;

	public FiniteDimensionalLinearMap(FiniteDimensionalVectorSpaceBasis domain, FiniteDimensionalVectorSpaceBasis range,
			FiniteMatrix matrix) {

		super(domain.getBaseSpace(), range.getBaseSpace());

		this.matrix = matrix;
		this.rangeBasis = range;
		this.domainBasis = domain;

		// get kernel: if M*B = CN and e in Kernel CN -> B*e in Kernel M
		int dimKer = matrix.getColumnNumber() - matrix.getRank();

		FiniteDimensionalVector[] kerVec = new FiniteDimensionalVector[dimKer];

		for (int i = matrix.getRank(); i < matrix.getColumnNumber(); i++) {

			RingElement[] vec = matrix.getColumnsNormalizer().matrix[i];

			kerVec[i - matrix.getRank()] = (FiniteDimensionalVector) domain.getBaseSpace().getNullElement();
			for (int j = 0; j < matrix.getColumnNumber(); j++)
				kerVec[i - matrix.getRank()] = (FiniteDimensionalVector) kerVec[i - matrix.getRank()]
						.add(domain.getBaseVectors()[j].multiply(vec[j]));
		}
		kernel = new VectorSpan(kerVec, domain.getBaseSpace());

		// get image: if A*M = RN and e in Image RN -> inv(A)*e in Image M
		FiniteDimensionalVector[] imVec = new FiniteDimensionalVector[matrix.getRank()];

		for (int i = 0; i < matrix.getRank(); i++) {

			RingElement[] vec = ((FiniteMatrix) matrix.getRowsNormalizer().getInverse()).matrix[i];

			imVec[i] = (FiniteDimensionalVector) range.getBaseSpace().getNullElement();
			for (int j = 0; j < matrix.getRownNumber(); j++)
				imVec[i] = (FiniteDimensionalVector) imVec[i].add(range.getBaseVectors()[j].multiply(vec[j]));
		}
		image = new VectorSpan(imVec, range.getBaseSpace());

		/*
		 * get the cokernel as a complement to the kernel: Let MC be Matrix from
		 * kernel basis to image basis: M*B = inv(A)*MC <-> A*M*B = MC (do
		 * summation!). For this use komplete Basis B of kernel + cokernel on
		 * domain and inv(A)*basis range on range -> kernel is mapped to 0 and
		 * non-image base vectors have 0 coordinate -> MC has 0 on right columns
		 * and bottom rows > rank -> take rank x rank matrix
		 */
		FiniteDimensionalVector[] cokerVec = new FiniteDimensionalVector[matrix.getRank()];
		FiniteMatrix isoFull = (FiniteMatrix) matrix.getRowsNormalizer().multiply(matrix)
				.multiply(matrix.getColumnsNormalizer());

		RingElement[][] isoEls = new RingElement[matrix.getRank()][matrix.getRank()];

		for (int i = 0; i < matrix.getRank(); i++) {

			RingElement[] vec = matrix.getColumnsNormalizer().matrix[i];

			cokerVec[i] = (FiniteDimensionalVector) domain.getBaseSpace().getNullElement();
			for (int j = 0; j < matrix.getRank(); j++) {
				isoEls[i][j] = isoFull.getElement(i, j);
				cokerVec[i] = (FiniteDimensionalVector) cokerVec[i].add(domain.getBaseVectors()[j].multiply(vec[j]));
			}
		}
		isoMatrix = new FiniteMatrix(matrix.getBaseRing(), isoEls);
		coKernel = new VectorSpan(cokerVec, domain.getBaseSpace());
	}

	public FiniteDimensionalVectorSpaceBasis getDomainBasis() {
		return domainBasis;
	}

	public FiniteDimensionalVectorSpaceBasis getRangeBasis() {
		return rangeBasis;
	}

	public FiniteMatrix getMatrix() {
		return matrix;
	}

	public FiniteDimensionalVector getImage(FiniteDimensionalVector vector) {

		FiniteDimensionalVector im = (FiniteDimensionalVector) this.rangeBasis.getBaseSpace().getNullElement();

		RingElement[] els = this.matrix.multiply(this.domainBasis.getCoordinates(vector));
		for (int i = 0; i < this.rangeBasis.getBaseVectors().length; i++)
			im = (FiniteDimensionalVector) im.add(this.rangeBasis.getBaseVectors()[i].multiply(els[i]));

		return im;
	}

	public Set getImage(Set set) {

		return this.getImage((FiniteDimensionalVector) set);
	}

	public Set getPreImage(Set image) {

		FiniteDimensionalVector vector = (FiniteDimensionalVector)image;
		if (!this.image.isElement(vector))
			return null;

		FiniteDimensionalVector pre = this.getIsoInverseMap().getImage(vector);

		return new AffineSubspace(this.domainBasis.getBaseSpace(), pre, this.kernel);
	}

	public Module getKernel() {

		return this.kernel;
	}

	public Module getImage() {

		return this.image;
	}

	public FiniteDimensionalLinearMap getIsoMap() {

		if (isoMap == null)
			isoMap = new FiniteDimensionalLinearMap(this.kernel.getBasis(), this.image.getBasis(), isoMatrix);

		return isoMap;
	}

	public FiniteDimensionalLinearMap getIsoInverseMap() {

		if (isoInverseMap == null)
			isoMap = this.getIsoMap().getIsoInverseMap();

		return isoInverseMap;
	}

	public LinearMap getInverse() {

		if (matrix.getInverse() == null)
			return null;

		if (inverseMap == null)
			inverseMap = new FiniteDimensionalLinearMap(this.rangeBasis, this.domainBasis,
					(FiniteMatrix) this.getMatrix().getInverse());

		return this.inverseMap;

	}
}
