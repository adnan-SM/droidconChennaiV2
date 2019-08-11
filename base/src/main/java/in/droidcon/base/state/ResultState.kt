package `in`.droidcon.base.state

/**
 * Created by Hari on 2019-08-11.
 * state for UI
 */
sealed class ResultState<out S, out F> {

    object Loading: ResultState<Nothing, Nothing>()

    class Success<out S>(val result: S): ResultState<S, Nothing>()

    class Failed<out F>(val message: F): ResultState<Nothing, F>()
}