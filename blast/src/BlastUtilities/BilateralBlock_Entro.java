package BlastUtilities;

import DataStructures.AbstractBlock;
import DataStructures.BilateralBlock;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author stravanni
 */
public class BilateralBlock_Entro extends AbstractBlock_Entro implements Serializable {
    /*
    * Only add entrpy at the class BilateralBlock
    * */

    private static final long serialVersionUID = 75264711552351524L;

    private final int[] index1Entities;
    private final int[] index2Entities;

    public BilateralBlock_Entro(int[] entities1, int[] entities2) {
        super();
        index1Entities = entities1;
        index2Entities = entities2;
        comparisons = ((double) index1Entities.length) * ((double) index2Entities.length);
    }

    public BilateralBlock_Entro(int[] entities1, int[] entities2, double e) {
        super(e);
        index1Entities = entities1;
        index2Entities = entities2;
        comparisons = ((double) index1Entities.length) * ((double) index2Entities.length);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BilateralBlock_Entro other = (BilateralBlock_Entro) obj;
        if (!Arrays.equals(this.index1Entities, other.index1Entities)) {
            return false;
        }
        if (!Arrays.equals(this.index2Entities, other.index2Entities)) {
            return false;
        }
        return true;
    }

    public int[] getIndex1Entities() {
        return index1Entities;
    }

    public int[] getIndex2Entities() {
        return index2Entities;
    }

    @Override
    public double getTotalBlockAssignments() {
        return index1Entities.length + index2Entities.length;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Arrays.hashCode(this.index1Entities);
        hash = 41 * hash + Arrays.hashCode(this.index2Entities);
        return hash;
    }

    @Override
    public void setUtilityMeasure() {
        utilityMeasure = 1.0 / Math.max(index1Entities.length, index2Entities.length);
    }
}