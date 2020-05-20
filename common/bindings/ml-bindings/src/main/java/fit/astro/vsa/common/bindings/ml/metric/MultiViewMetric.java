/*
 * Copyright (C) 2018 Kyle Johnston <kyjohnst2000@my.fit.edu>
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
package fit.astro.vsa.common.bindings.ml.metric;

import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author Kyle Johnston <kyjohnst2000@my.fit.edu>
 */
public class MultiViewMetric {

    private final RealMatrix mk;
    private final double weight;

    public MultiViewMetric(RealMatrix mk, double weight) {
        this.mk = mk;
        this.weight = weight;
    }

    public RealMatrix getMk() {
        return mk;
    }

    public double getWeight() {
        return weight;
    }

}
