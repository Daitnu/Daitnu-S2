import { all, takeLatest } from 'redux-saga/effects';
import { REGISTER_REQUEST, REGISTER } from './actions';
import { makeApiCallSagaFunc } from '~/redux/util';
import sagaApiFuncParams from '~/redux/util/sagaApiFuncParams';

const register$ = makeApiCallSagaFunc(REGISTER, sagaApiFuncParams.REGISTER);

export function* userRegisterSaga() {
  yield all([takeLatest(REGISTER_REQUEST, register$)]);
}
