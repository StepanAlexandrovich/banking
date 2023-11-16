CREATE OR REPLACE PROCEDURE public.transfer(
	from_id bigint,
	to_id bigint,
	transfer_amount double precision)
LANGUAGE 'plpgsql'
AS $BODY$
declare from_amount double precision;
declare to_amount double precision;

begin

select amount into strict from_amount from public.accounts where id = from_id;
select amount into strict to_amount from public.accounts where id = to_id;

update public.accounts set amount = from_amount - transfer_amount  where id = from_id;
update public.accounts set amount = to_amount + transfer_amount  where id = to_id;

end;
$BODY$;