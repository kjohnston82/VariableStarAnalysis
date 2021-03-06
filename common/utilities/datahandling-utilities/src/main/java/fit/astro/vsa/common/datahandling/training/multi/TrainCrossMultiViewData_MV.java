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
package fit.astro.vsa.common.datahandling.training.multi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author Kyle Johnston <kyjohnst2000@my.fit.edu>
 */
public class TrainCrossMultiViewData_MV {

    private final Map<Integer, Map<String, RealMatrix>> setOfTrainingPatterns;
    private final Map<Integer, String> setOfTrainingClasses;

    private final Map<Integer, Map<String, RealMatrix>> setOfCrossvalPatterns;
    private final Map<Integer, String> setOfCrossvalClasses;

    /**
     * 
     * @param setOfPatterns
     * @param setOfClasses
     * @param crossvalMap
     * @param idx 
     */
    public TrainCrossMultiViewData_MV(
            Map<Integer, Map<String, RealMatrix>> setOfPatterns,
            Map<Integer, String> setOfClasses,
            Map<Integer, List<Integer>> crossvalMap,
            Integer idx) {

        // Decompose the 5-Fold Crossval
        List<Integer> trainingIdx = new ArrayList<>();
        List<Integer> crossvalIdx = new ArrayList<>();

        crossvalMap.keySet().stream().forEach((jdx) -> {
            if (idx.equals(jdx)) {
                crossvalIdx.addAll(crossvalMap.get(jdx));
            } else {
                trainingIdx.addAll(crossvalMap.get(jdx));
            }
        });

        this.setOfTrainingPatterns = new HashMap<>(trainingIdx.size());
        this.setOfTrainingClasses = new HashMap<>(trainingIdx.size());
        this.setOfCrossvalPatterns = new HashMap<>(crossvalIdx.size());
        this.setOfCrossvalClasses = new HashMap<>(crossvalIdx.size());
        
        crossvalIdx.stream().map((jdx) -> {
            setOfCrossvalPatterns.put(jdx, setOfPatterns.get(jdx));
            return jdx;
        }).forEach((jdx) -> {
            setOfCrossvalClasses.put(jdx, setOfClasses.get(jdx));
        });

        trainingIdx.stream().map((jdx) -> {
            setOfTrainingPatterns.put(jdx, setOfPatterns.get(jdx));
            return jdx;
        }).forEach((jdx) -> {
            setOfTrainingClasses.put(jdx, setOfClasses.get(jdx));
        });
    }

    /**
     * @return the setOfTrainingPatterns
     */
    public Map<Integer, Map<String, RealMatrix>> getSetOfTrainingPatterns() {
        return setOfTrainingPatterns;
    }

    /**
     * @return the setOfTrainingClasses
     */
    public Map<Integer, String> getSetOfTrainingClasses() {
        return setOfTrainingClasses;
    }

    /**
     * @return the setOfCrossvalPatterns
     */
    public Map<Integer, Map<String, RealMatrix>> getSetOfCrossvalPatterns() {
        return setOfCrossvalPatterns;
    }

    /**
     * @return the setOfCrossvalClasses
     */
    public Map<Integer, String> getSetOfCrossvalClasses() {
        return setOfCrossvalClasses;
    }

}
