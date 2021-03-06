/*
 * Copyright (C) 2016 Kyle Johnston
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fit.astro.vsa.common.utilities.clustering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

/**
 * MacQueen, J. B. (1967). Some Methods for classification and Analysis of
 * Multivariate Observations. Proceedings of 5th Berkeley Symposium on
 * Mathematical Statistics and Probability. 1. University of California Press.
 * pp. 281–297. MR 0214227. Zbl 0214.46201. Retrieved 2009-04-07.
 *
 * <p>
 * Friedman, J., Hastie, T., & Tibshirani, R. (2001). The elements of
 * statistical learning (Vol. 1, No. 10). New York, NY, USA:: Springer series in
 * statistics.
 * @author Kyle Johnston <kyjohnst2000@my.fit.edu>
 */
public class KMeansClustering {

    // =============================================================
    // Input
    private final Map<Integer, RealVector> setOfTrainingData;

    private int numClusters;
    private final Random rnd;

    // =============================================================
    // Internal
    private final int patternSize;

    private Map<Integer, RealVector> clusterCenters;
    private Map<Integer, List<Integer>> clusterMembers;

    /**
     * Input training data, un-supervised clustering
     * @param setOfTrainingData
     */
    public KMeansClustering(Map<Integer, RealVector> setOfTrainingData) {
        this.setOfTrainingData = setOfTrainingData;
        this.rnd = new Random(42L);

        int select = rnd.nextInt(setOfTrainingData.size());

        List<Integer> idxList = new ArrayList<>(
                setOfTrainingData.keySet());

        RealVector firstVector
                = setOfTrainingData.get(idxList.get(select));

        this.patternSize = firstVector.getDimension();
    }

    /**
     * Input training data, un-supervised clustering
     * @param setOfTrainingData
     * @param rnd
     */
    public KMeansClustering(Map<Integer, RealVector> setOfTrainingData,
            Random rnd) {
        this.setOfTrainingData = setOfTrainingData;
        this.rnd = rnd;
        int select = rnd.nextInt(setOfTrainingData.size());

        List<Integer> idxList = new ArrayList<>(
                setOfTrainingData.keySet());

        RealVector firstVector
                = setOfTrainingData.get(idxList.get(select));

        this.patternSize = firstVector.getDimension();
    }

    /**
     * Split into x number of clusters
     * @param numClusters
     * @return
     */
    public Map<Integer, List<Integer>> execute(int numClusters) {

        this.numClusters = numClusters;
        initializeVariables();

        boolean isTheSame = Boolean.TRUE;

        while (isTheSame) {

            // new list
            Map<Integer, List<Integer>> clusterMembersK = new HashMap<>();

            // Step 1
            for (Integer clusterIdx : clusterCenters.keySet()) {

                // Find Center Means
                RealVector tmpClusterMean = new ArrayRealVector(patternSize);

                for (Integer memberIdx : clusterMembers.get(clusterIdx)) {

                    tmpClusterMean = tmpClusterMean.add(
                            setOfTrainingData.get(memberIdx));
                }

                tmpClusterMean = tmpClusterMean
                        .mapDivide((double) clusterMembers.get(clusterIdx).size());

                clusterCenters.put(clusterIdx, tmpClusterMean);

                // Initialize New Clusters
                clusterMembersK.put(clusterIdx, new ArrayList<>());
            }

            // Step 2
            for (Integer idx : setOfTrainingData.keySet()) {

                RealVector currentData = setOfTrainingData.get(idx);
                List<Double> distanceEst = distanceToMeans(currentData);

                int minIndex = distanceEst
                        .indexOf(Collections.min(distanceEst));

                clusterMembersK.get(minIndex).add(idx);
            }

            //Estimate Differential
            boolean oneDifferent = Boolean.FALSE;

            for (Integer clusterIdx : clusterCenters.keySet()) {

                List<Integer> kSet = clusterMembersK.get(clusterIdx);
                List<Integer> priorSet = clusterMembers.get(clusterIdx);

                if (!kSet.equals(priorSet)) {
                    oneDifferent = Boolean.TRUE;
                }
            }

            clusterMembers = clusterMembersK;

            if (!oneDifferent) {
                isTheSame = Boolean.FALSE;
            }

        }

        return clusterMembers;
    }

    /**
     * L2-Norm
     *
     * @param currentData
     * @return
     */
    private List<Double> distanceToMeans(RealVector currentData) {

        List<Double> distanceEst = new ArrayList<>();

        clusterCenters.keySet().forEach((idx) -> distanceEst
                .add(idx, currentData.getDistance(clusterCenters.get(idx))));

        return distanceEst;
    }

    /**
     * Initialize the variable to handle the data
     */
    private void initializeVariables() {

        //Estimate Inital Expectation of Memebership
        clusterMembers = new HashMap<>();
        clusterCenters = new HashMap<>();

        // Initialize Cluster Member
        for (int clusterID = 0; clusterID < numClusters; clusterID++) {
            clusterMembers.put(clusterID, new ArrayList<>());
            clusterCenters.put(clusterID, setOfTrainingData.get(clusterID));
        }

        // Initialized Cluster
        setOfTrainingData.keySet().forEach((idx) -> clusterMembers.get((int) Math.floor(numClusters * rnd.nextDouble())).add(idx));

    }

}
