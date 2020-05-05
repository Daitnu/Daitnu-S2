import { LOGIN } from './actions';
import { makeReducer } from '~/redux/util';
import { ResponseLogin } from '~/@types/response/user';
import { LoginParam } from '~/@types/request/user';

const userLoginReducer = makeReducer<ResponseLogin, LoginParam>(LOGIN);

export default userLoginReducer;
