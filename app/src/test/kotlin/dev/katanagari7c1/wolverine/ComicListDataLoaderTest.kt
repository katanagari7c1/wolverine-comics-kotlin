package dev.katanagari7c1.wolverine

import com.nhaarman.mockito_kotlin.*
import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindAllFromOffsetUseCase
import dev.katanagari7c1.wolverine.domain.use_case.ComicSaveOrUpdateUseCase
import dev.katanagari7c1.wolverine.presentation.main.data_loader.ComicListDataLoader
import org.junit.Before
import org.junit.Test

class ComicListDataLoaderTest {

	private lateinit var loadWithOffsetUseCase: ComicFindAllFromOffsetUseCase
	private lateinit var saveUseCase: ComicSaveOrUpdateUseCase
	private lateinit var dataLoader: ComicListDataLoader

	private val comicsFirstPage = listOf(
		Comic(comicId = "1"),
		Comic(comicId = "2"),
		Comic(comicId = "3"),
		Comic(comicId = "4"),
		Comic(comicId = "5")
	)

	private val comicsSecondPage = listOf(
		Comic(comicId = "6"),
		Comic(comicId = "7"),
		Comic(comicId = "8")
	)

	@Before
	fun setup() {
		this.loadWithOffsetUseCase = mock()
		this.saveUseCase = mock()

		whenever(loadWithOffsetUseCase.execute(any(), any()))
			.thenReturn(comicsFirstPage, comicsSecondPage, listOf())

		this.dataLoader = ComicListDataLoader(loadWithOffsetUseCase, saveUseCase)
	}

	@Test
	fun test_first_load_requested_with_zero_offset() {
		this.dataLoader.load()

		verify(loadWithOffsetUseCase).execute(0, dataLoader.numberOfItemsToLoad)
	}

	@Test
	fun test_another_load_call_increases_the_offset() {
		this.dataLoader.load()
		verify(loadWithOffsetUseCase).execute(0, dataLoader.numberOfItemsToLoad)
		this.dataLoader.load()
		verify(loadWithOffsetUseCase).execute(5, dataLoader.numberOfItemsToLoad)
	}

	@Test
	fun test_use_case_is_not_called_when_reached_the_end() {
		this.dataLoader.load()
		this.dataLoader.load()
		this.dataLoader.load()
		this.dataLoader.load()

		verify(loadWithOffsetUseCase, times(3)).execute(any(), any())
	}

	@Test
	fun test_save_use_case_is_called_if_load_requested() {
		this.dataLoader.load()

		verify(saveUseCase, atLeastOnce()).execute(any())
	}
}
