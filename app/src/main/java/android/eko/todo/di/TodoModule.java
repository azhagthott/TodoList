package android.eko.todo.di;

import android.eko.core.data.remote.ApiServiceFactory;
import android.eko.todo.BuildConfig;
import android.eko.todo.data.remote.TodoApi;
import android.eko.todo.data.repository.TodoRepository;
import android.eko.todo.data.repository.TodoRepositoryImp;
import android.eko.todo.data.repository.mapper.TodoEntityToDomainMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class TodoModule {

    @Provides
    TodoRepository provideRepository(TodoApi api, TodoEntityToDomainMapper mapper) {
        return new TodoRepositoryImp(api, mapper);
    }

    @Provides
    @Singleton
    TodoApi provideApiService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BASIC);
        logging.level(HttpLoggingInterceptor.Level.HEADERS);
        logging.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        return ApiServiceFactory.build(okHttpClient, TodoApi.class, BuildConfig.URL_BASE);
    }
}
