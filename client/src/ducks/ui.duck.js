import { getCurrentList } from './'

export const CHANGE_PAGE = 'CHANGE_PAGE'
export const SELECT_LIBRARY = 'SELECT_LIBRARY'
export const SELECT_TRASH = 'SELECT_TRASH'
export const OPEN_FOLDER = 'OPEN_FOLDER'

const initialState = {
  currentFolder: null,
  currentPage: 1,
  trashLoaded: false
}

export default function config (state = initialState, action) {
  switch (action.type) {
    case SELECT_TRASH:
      return {
        ...state,
        currentPage: 1,
        trashLoaded: true,
        currentFolder: null
      }
    case SELECT_LIBRARY:
      return {
        ...state,
        currentPage: 1,
        trashLoaded: false,
        currentFolder: null
      }
    case OPEN_FOLDER:
      return {
        ...state,
        currentFolder: action.folder
      }
    case CHANGE_PAGE:
      return {
        ...state,
        currentPage: action.page
      }
    default:
      return state
  }
}

export const openFolder = folder => dispatch => {
  dispatch({ type: OPEN_FOLDER, folder })
  dispatch(getCurrentList())
}

export const changePage = page => dispatch => {
  dispatch({ type: CHANGE_PAGE, page })
  dispatch(getCurrentList())
}

export const selectTrash = () => dispatch => {
  dispatch({ type: SELECT_TRASH })
  dispatch(getCurrentList())
}

export const selectLibrary = () => dispatch => {
  dispatch({ type: SELECT_LIBRARY })
  dispatch(getCurrentList())
}