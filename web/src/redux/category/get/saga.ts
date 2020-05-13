import { all, takeLatest } from 'redux-saga/effects';
import CategoryApi from '~/library/request/CategoryApi';
import { makeApiCallSagaFunc } from '~/redux/util';
import { CATEGORY_GET, CATEGORY_GET_REQUEST } from './actions';

const api = new CategoryApi();

const categoryGet$ = makeApiCallSagaFunc({
  type: CATEGORY_GET,
  apiFunc: api.getCategories.bind(api),
});

export function* categoryGetSaga() {
  yield all([takeLatest(CATEGORY_GET_REQUEST, categoryGet$)]);
}
