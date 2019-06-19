package `in`.droidcon.base.state

/**
 * @author Adnan A M
 * @since  19/03/19
 */
sealed class TaskState {

    object Loading: TaskState()

    class Success<out T>(val result: T): TaskState()

    class Failed(val message: String): TaskState()
}