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
package fit.astro.vsa.common.bindings.analysis.astro;

/**
 * Time domain statistic measurements
 *
 * @author Kyle Johnston <kyjohnst2000@my.fit.edu>
 */
public enum TimeDomainStatistics {

    Ampl("mean waveform"), skew("skew waveform"), kurt("kurtosis amplitude"),
    magMed("median magnitude");

    private final String methodLabel;

    TimeDomainStatistics(String methodLabel) {
        this.methodLabel = methodLabel;
    }

    /**
     *
     * @return Label for what it is
     */
    public String getMethodLabel() {
        return methodLabel;
    }

}
