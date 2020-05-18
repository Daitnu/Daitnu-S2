import { User } from '../state/user';
import { BusinessErrorResponse } from '../response/error';

export interface ValidateParam {
  val: string;
  min: number;
  max: number;
}

export interface IsLoginParam {
  user: User;
  error: BusinessErrorResponse | null;
}
