package com.example.bostatask.data.paging
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bostatask.domain.models.Game
import com.example.bostatask.data.mapper.toDomain
import com.example.bostatask.data.remote.RawgApi

class GamesPagingSource(
    private val api: RawgApi,
    private val genreId: Int
) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val page = params.key ?: 1

            val response = api.getGames(
                genreId = genreId,
                page = page,
                pageSize = params.loadSize
            )

            val games = response.results?.map { it.toDomain() }

            LoadResult.Page(
                data = games ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}