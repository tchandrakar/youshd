package utilities

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

object DBUtils {
  implicit class Safely[T](val f: Future[T]) {
    type SafelyReturned = Either[Throwable, T]

    /** Converts the Future into a 'recovered' Future that resolves to an Either block holding either the result or
     * a Throwable based on whether the Future could complete successfully.
     *
     * @return A Future with Right(result), if the Future completes successfully, or Left(t), where t is a Throwable,
     *         if the Future fails due to a thrown exception
     */
    def safely(implicit ec: ExecutionContext): Future[SafelyReturned] =
      f.map(Right(_): SafelyReturned).recover { case t => Left(t) }
  }

  implicit class TrySafely[R](val tryBlock: Try[Future[Either[Throwable, R]]]) {

    /**
     * Converts a Try-wrapped 'recovered' Future to an Either holding either the result or a Throwable arising from the
     * Future or from the Try block.
     */
    def flattenedEither: Future[Either[Throwable, R]] = tryBlock match {
      case Failure(t) => Future.successful(Left(t))
      case Success(res) => res
    }
  }
}
