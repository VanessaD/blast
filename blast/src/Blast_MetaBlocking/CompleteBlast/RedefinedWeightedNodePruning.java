/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    Copyright (C) 2015 George Antony Papadakis (gpapadis@yahoo.gr)
 */

package Blast_MetaBlocking.CompleteBlast;

import Blast_MetaBlocking.ThresholdWeightingScheme;
import DataStructures.AbstractBlock;
import MetaBlocking.WeightingScheme;

import java.util.List;

/**
 * @author G.A.P. II
 */
public class RedefinedWeightedNodePruning extends WeightedNodePruning {

    protected double[] averageWeight;

    public RedefinedWeightedNodePruning(WeightingScheme scheme) {
        this("Redundancy Weighted Node Pruning (" + scheme + ")", scheme);
    }

    public RedefinedWeightedNodePruning(WeightingScheme scheme, ThresholdWeightingScheme threshold_type) {
        this("Redundancy Weighted Node Pruning (" + scheme + ")", scheme, threshold_type);
    }

    public RedefinedWeightedNodePruning(WeightingScheme scheme, boolean t) {
        this("Redundancy Weighted Node Pruning (" + scheme + ")", scheme, t);
    }

    protected RedefinedWeightedNodePruning(String description, WeightingScheme scheme) {
        super(description, scheme);
    }

    protected RedefinedWeightedNodePruning(String description, WeightingScheme scheme, boolean t) {
        super(description, scheme, t);
    }

    protected RedefinedWeightedNodePruning(String description, WeightingScheme scheme, ThresholdWeightingScheme threshld_type) {
        super(description, scheme, threshld_type);
    }

    protected boolean isValidComparison(int entityId, int neighborId) {
        double weight = getWeight(entityId, neighborId);
        boolean inNeighborhood1 = averageWeight[entityId] <= weight;
        boolean inNeighborhood2 = averageWeight[neighborId] <= weight;

        if (inNeighborhood1 || inNeighborhood2) {
            return entityId < neighborId;
        }

        return false;
    }

    @Override
    protected void pruneEdges(List<AbstractBlock> newBlocks) {
        if (weightingScheme.equals(WeightingScheme.ARCS)) {
            for (int i = 0; i < noOfEntities; i++) {
                processArcsEntity(i);
                verifyValidEntities(i, newBlocks);
            }
        }
//        else if (weightingScheme.equals(WeightingScheme.CHI_ENTRO)) {
//            System.out.println("\n\n\n chi entro in redefined wnp\n\n\n");
//            for (int i = 0; i < noOfEntities; i++) {
//                processCHI_entro_Entity(i);
//                verifyValidEntities(i, newBlocks);
//            }
//        }
        else {
            for (int i = 0; i < noOfEntities; i++) {
                processEntity(i);
                verifyValidEntities(i, newBlocks);
            }
        }
    }

    @Override
    protected void setThreshold() {
        averageWeight = new double[noOfEntities];
        if (weightingScheme.equals(WeightingScheme.ARCS)) {
            for (int i = 0; i < noOfEntities; i++) {
                processArcsEntity(i);
                setThreshold(i);
                averageWeight[i] = threshold;
            }
        } else {
            for (int i = 0; i < noOfEntities; i++) {
                processEntity(i);
                setThreshold(i);
                averageWeight[i] = threshold;
            }
        }
    }

    @Override
    protected void verifyValidEntities(int entityId, List<AbstractBlock> newBlocks) {
        retainedNeighbors.clear();
        if (!cleanCleanER) {
            for (int neighborId : validEntities) {
                if (isValidComparison(entityId, neighborId)) {
                    retainedNeighbors.add(neighborId);
                }
            }
            addDecomposedBlock(entityId, retainedNeighbors, newBlocks);
        } else {
            if (entityId < datasetLimit) {
                for (int neighborId : validEntities) {
                    if (isValidComparison(entityId, neighborId)) {
                        retainedNeighbors.add(neighborId - datasetLimit);
                    }
                }
                addDecomposedBlock(entityId, retainedNeighbors, newBlocks);
            } else {
                for (int neighborId : validEntities) {
                    if (isValidComparison(entityId, neighborId)) {
                        retainedNeighbors.add(neighborId);
                    }
                }
                addReversedDecomposedBlock(entityId - datasetLimit, retainedNeighbors, newBlocks);
            }
        }
    }
}
