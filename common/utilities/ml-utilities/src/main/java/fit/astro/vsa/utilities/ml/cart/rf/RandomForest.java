/*
 * Copyright (C) 2019 kjohnston
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
package fit.astro.vsa.utilities.ml.cart.rf;

import fit.astro.vsa.utilities.ml.cart.CART;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kjohnston
 */
public class RandomForest {

    private final List<CART> rf;
    private final Map<String, Integer> uniqueLabelCount;

    public RandomForest(List<CART> rf, Map<String, Integer> uniqueLabelCount) {
        this.rf = rf;
        this.uniqueLabelCount = uniqueLabelCount;
    }

    public List<CART> getRf() {
        return rf;
    }

    public Map<String, Integer> getUniqueLabelCount() {
        return uniqueLabelCount;
    }

}
