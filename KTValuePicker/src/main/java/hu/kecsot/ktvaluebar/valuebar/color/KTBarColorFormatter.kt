package hu.kecsot.ktvaluebar.valuebar.color

/**
 * Interface for providing custom colors for the ValueBar.
 *
 * @author Philipp Jahoda
 */
interface KTBarColorFormatter {

    /**
     * Use this method to return whatever color you like the ValueBar to have.
     * You can also make use of the current value the bar has.
     */
    fun getColor(value: Float, maxVal: Float, minVal: Float): Int
}
