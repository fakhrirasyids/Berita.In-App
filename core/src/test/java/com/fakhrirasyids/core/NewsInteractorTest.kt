package com.fakhrirasyids.core

import com.fakhrirasyids.core.data.Resource
import com.fakhrirasyids.core.domain.repository.INewsRepository
import com.fakhrirasyids.core.domain.usecase.NewsInteractor
import com.fakhrirasyids.core.utils.CoroutineTestRule
import com.fakhrirasyids.core.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsInteractorTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var newsInteractor: NewsInteractor

    @Mock
    private lateinit var newsRepository: INewsRepository

    private val dummyLatestNewsList = DataDummy.generateDummyLatestNewsList()
    private val dummyCategoryNewsList = DataDummy.generateDummyCategoryNewsList()

    @Before
    fun setUp() {
        newsInteractor = NewsInteractor(newsRepository)
    }

    @Test
    fun `Successfully get latestNews`() {
        val expectedResult = flowOf(Resource.Success(dummyLatestNewsList))

        `when`(newsRepository.getLatestNews()).thenReturn(
            expectedResult
        )

        val actualResult = newsInteractor.getLatestNews()

        assertEquals(expectedResult, actualResult)
        verify(newsRepository).getLatestNews()
    }

    @Test
    fun `Successfully get businessNews`() {
        val expectedResult = flowOf(Resource.Success(dummyCategoryNewsList))

        `when`(newsRepository.getBusinessNews()).thenReturn(
            expectedResult
        )

        val actualResult = newsInteractor.getBusinessNews()

        assertEquals(expectedResult, actualResult)
        verify(newsRepository).getBusinessNews()
    }

    @Test
    fun `Successfully get entertainmentNews`() {
        val expectedResult = flowOf(Resource.Success(dummyCategoryNewsList))

        `when`(newsRepository.getEntertainmentNews()).thenReturn(
            expectedResult
        )

        val actualResult = newsInteractor.getEntertainmentNews()

        assertEquals(expectedResult, actualResult)
        verify(newsRepository).getEntertainmentNews()
    }

    @Test
    fun `Successfully get healthNews`() {
        val expectedResult = flowOf(Resource.Success(dummyCategoryNewsList))

        `when`(newsRepository.getHealthNews()).thenReturn(
            expectedResult
        )

        val actualResult = newsInteractor.getHealthNews()

        assertEquals(expectedResult, actualResult)
        verify(newsRepository).getHealthNews()
    }

    @Test
    fun `Successfully get scienceNews`() {
        val expectedResult = flowOf(Resource.Success(dummyCategoryNewsList))

        `when`(newsRepository.getScienceNews()).thenReturn(
            expectedResult
        )

        val actualResult = newsInteractor.getScienceNews()

        assertEquals(expectedResult, actualResult)
        verify(newsRepository).getScienceNews()
    }

    @Test
    fun `Successfully get sportsNews`() {
        val expectedResult = flowOf(Resource.Success(dummyCategoryNewsList))

        `when`(newsRepository.getSportsNews()).thenReturn(
            expectedResult
        )

        val actualResult = newsInteractor.getSportsNews()

        assertEquals(expectedResult, actualResult)
        verify(newsRepository).getSportsNews()
    }

    @Test
    fun `Successfully get technologyNews`() {
        val expectedResult = flowOf(Resource.Success(dummyCategoryNewsList))

        `when`(newsRepository.getTechnologyNews()).thenReturn(
            expectedResult
        )

        val actualResult = newsInteractor.getTechnologyNews()

        assertEquals(expectedResult, actualResult)
        verify(newsRepository).getTechnologyNews()
    }

    @Test
    fun `Successfully get searchedNews`() {
        val expectedResult = flowOf(Resource.Success(dummyCategoryNewsList))

        `when`(newsRepository.searchNews(QUERY)).thenReturn(
            expectedResult
        )

        val actualResult = newsInteractor.searchNews(QUERY)

        assertEquals(expectedResult, actualResult)
        verify(newsRepository).searchNews(QUERY)
    }

    companion object {
        const val QUERY = "query"
    }
}