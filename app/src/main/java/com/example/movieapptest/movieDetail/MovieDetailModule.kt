import com.example.movieapptest.data.network.retrofit.ServiceInjector
import com.example.movieapptest.movieDetail.MovieDetailRepositoryImpl
import com.example.movieapptest.movieDetail.MovieDetailService
import com.example.movieapptest.movieDetail.MovieDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailModule = module {

    single {
        ServiceInjector.getMovieDetailService()
    }

    single {
        MovieDetailRepositoryImpl(get())
    }

    viewModel {
        MovieDetailViewModel(get(), get())
    }

}