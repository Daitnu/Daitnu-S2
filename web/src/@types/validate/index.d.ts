export interface ValidateParam {
  val: string;
  min: number;
  max: number;
}

export interface IsLoginParam {
  userId: string | null;
  userName: string | null;
}
