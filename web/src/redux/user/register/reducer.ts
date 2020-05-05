import { REGISTER } from './actions';
import { makeReducer } from '~/redux/util';
import { ResponseRegister } from '~/@types/response/user';
import { RegisterParam } from '~/@types/request/user';

const userRegisterReducer = makeReducer<ResponseRegister, RegisterParam>(REGISTER);

export default userRegisterReducer;
