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
package fit.astro.vsa.utilities.ml.lrc;

import java.util.Map;
import org.apache.commons.math3.linear.RealVector;

/**
 *
 * @author Kyle Johnston <kyjohnst2000@my.fit.edu>
 */
public class LRC {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    private final Map<String, RealVector> betaParams;
    private final Map<String, Integer> trainingDataCount;

    // </editor-fold>
    
    /**
     * 
     * @param betaParams
     * @param trainingDataCount 
     */
    public LRC(Map<String, RealVector> betaParams,
            Map<String, Integer> trainingDataCount) {
        this.betaParams = betaParams;
        this.trainingDataCount = trainingDataCount;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    /**
     * 
     * @return 
     */
    public Map<String, RealVector> getBetaParams() {
        return betaParams;
    }

    /**
     * 
     * @return 
     */
    public Map<String, Integer> getTrainingDataCount() {
        return trainingDataCount;
    }

    // </editor-fold>
}
